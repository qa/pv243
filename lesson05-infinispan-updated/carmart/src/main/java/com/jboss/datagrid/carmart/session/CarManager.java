/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.jboss.datagrid.carmart.session;

import org.infinispan.commons.api.BasicCache;
import com.jboss.datagrid.carmart.model.Car;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.Search;
import org.infinispan.query.SearchManager;
import java.util.ArrayList;
import org.infinispan.Cache;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * Adds, retrieves, removes new cars from the cache. Also returns a list of cars stored in the cache.
 * 
 * @author Martin Gencur
 * 
 */
@Model
public class CarManager {

    public static final String CAR_CACHE_NAME = "carcache";
    public static final String CAR_LIST_CACHE_NAME = "carlist";

    public static final String CAR_NUMBERS_KEY = "carnumbers";

    @Inject
    private CacheContainerProvider provider;

    private BasicCache<String, Object> carCache;

    private BasicCache<String, Object> carListCache;

    private List<Car> searchResults;

    @Inject
    private UserTransaction utx;

    private String carId;
    private Car car = new Car();

    public CarManager() {
    }

    public String addNewCar() {
        carCache = provider.getCacheContainer().getCache(CAR_CACHE_NAME);
        carListCache = provider.getCacheContainer().getCache(CarManager.CAR_LIST_CACHE_NAME);
        try {
            utx.begin();
            List<String> carNumbers = getNumberPlateList(carListCache);
            carNumbers.add(car.getNumberPlate());
            carListCache.put(CAR_NUMBERS_KEY, carNumbers);
            carCache.put(CarManager.encode(car.getNumberPlate()), car);
            utx.commit();
        } catch (Exception e) {
            if (utx != null) {
                try {
                    utx.rollback();
                } catch (Exception e1) {
                }
            }
        }
        return "home";
    }
    
    /**
     * Operate on a clone of car number list
     */
    @SuppressWarnings("unchecked")
    private List<String> getNumberPlateList(BasicCache<String, Object> carListCacheLoc) {
        List<String> result = null;
        List<String> carNumberList = (List<String>) carListCacheLoc.get(CAR_NUMBERS_KEY);
        if (carNumberList == null) {
            result = new LinkedList<String>();
        } else {
            result = new LinkedList<String>(carNumberList);
        }
        return result;
    }

    public String showCarDetails(String numberPlate) {
        System.out.println(numberPlate);
        carCache = provider.getCacheContainer().getCache(CAR_CACHE_NAME);
        this.car = (Car) carCache.get(encode(numberPlate));
        return "showdetails";
    }

    public List<String> getCarList() {
        carListCache = provider.getCacheContainer().getCache(CarManager.CAR_LIST_CACHE_NAME);
        // retrieve a list of number plates from the cache
        return getNumberPlateList(carListCache);
    }

    public String clearSearchResults() {
       searchResults = null;
       return "home";
    }

    public boolean isSearchActive() {
       return searchResults != null;
    }

    public String removeCar(String numberPlate) {
        carCache = provider.getCacheContainer().getCache(CAR_CACHE_NAME);
        carListCache = provider.getCacheContainer().getCache(CarManager.CAR_LIST_CACHE_NAME);
        try {
            utx.begin();
            carCache.remove(encode(numberPlate));
            List<String> carNumbers = getNumberPlateList(carListCache);
            carNumbers.remove(numberPlate);
            //if (true) throw new RuntimeException("Induced exception");
            carListCache.put(CAR_NUMBERS_KEY, carNumbers);
            utx.commit();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            if (utx != null) {
                try {
                    utx.rollback();
                } catch (Exception e1) {
                }
            }
        }
        return null;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public static String encode(String key) {
        try {
            return URLEncoder.encode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decode(String key) {
        try {
            return URLDecoder.decode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String search() {
       carCache = provider.getCacheContainer().getCache(CAR_CACHE_NAME);
       SearchManager sm = Search.getSearchManager((Cache) carCache);

//       //match any of the parameters----------------------------------------------------------------
//       QueryBuilder queryBuilder = sm.buildQueryBuilderForClass(Car.class).get();
//       Query q1 = queryBuilder
//                     .bool()
//                        .should(queryBuilder.keyword().onFields("brand")
//                                .matching(car.getBrand().isEmpty() ? "none" : car.getBrand())
//                                .createQuery())
//                        .should(queryBuilder.keyword().onFields("color")
//                                .matching(car.getColor().isEmpty() ? "none" : car.getColor())
//                                .createQuery())
//                        .should(queryBuilder.keyword().onFields("country")
//                                .matching(car.getCountry())
//                                .createQuery())
//                     .createQuery();
//       System.out.println("Lucene Query: " + q1.toString() ); //printing out raw Lucene query


       //match all the parameters----------------------------------------------------------------------
       BooleanQuery q2 = new BooleanQuery();

       if (!car.getBrand().isEmpty()) {
          Query query = new TermQuery(new Term("brand", car.getBrand().toLowerCase()));
          q2.add(query, BooleanClause.Occur.MUST);
       }
       if (!car.getColor().isEmpty()) {
          Query query = new TermQuery(new Term("color", car.getColor().toLowerCase()));
          q2.add(query, BooleanClause.Occur.MUST);
       }
       if (!car.getCountry().equals(Car.Country.Unused)) {
          Query query = new TermQuery(new Term("country", car.getCountry().toString().toLowerCase()));
          q2.add(query, BooleanClause.Occur.MUST);
       }
       System.out.println("Lucene Query: " + q2.toString() ); //printing out raw Lucene query

       //create a cache query based on the Lucene query
       CacheQuery cq = sm.getQuery(q2, Car.class);
       searchResults = new ArrayList<Car>();
       //invoke the cache query
       for (Object o : cq.list()) {
          if (o instanceof Car) {
             searchResults.add(((Car) o));
          }
       }
       return "searchresults";
    }

    public List<Car> getSearchResults() {
       return searchResults;
    }

}
