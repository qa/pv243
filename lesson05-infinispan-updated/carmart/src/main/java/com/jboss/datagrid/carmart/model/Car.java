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
package com.jboss.datagrid.carmart.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.ProvidedId;
import org.hibernate.search.bridge.builtin.StringBridge;

import java.io.Serializable;

/**
 * Represents a car in the car mart. Car objects are stored in the cache.
 * 
 * @author Martin Gencur
 * 
 */
@Indexed(index="CarIndex")
public class Car implements Serializable {

    private static final long serialVersionUID = 188164481825309731L;

    public enum CarType {
        SEDAN, HATCHBACK, COMBI, CABRIO, ROADSTER
    }

    public enum Country {
        Unused, CZECH_REPUBLIC, USA, GERMANY
    }

    public Car() {
    }

    public Car(String brand, double displacement, CarType type, String color, String numberPlate, Country country) {
        this.brand = brand;
        this.displacement = displacement;
        this.type = type;
        this.color = color;
        this.numberPlate = numberPlate;
        this.country = country;
    }

    @Field
    private String brand;

    @Field
    private double displacement;

    @Field
    private CarType type;

    @Field
    private String color;

    @Field
    private Country country;

    @Field
    private String numberPlate;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand.trim();
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color.trim();
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       Car car = (Car) o;
       if (Double.compare(car.displacement, displacement) != 0) return false;
       if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
       if (color != null ? !color.equals(car.color) : car.color != null) return false;
       if (country != car.country) return false;
       if (numberPlate != null ? !numberPlate.equals(car.numberPlate) : car.numberPlate != null) return false;
       if (type != car.type) return false;
       return true;
    }

    @Override
    public int hashCode() {
       int result;
       long temp;
       result = brand != null ? brand.hashCode() : 0;
       temp = displacement != +0.0d ? Double.doubleToLongBits(displacement) : 0L;
       result = 31 * result + (int) (temp ^ (temp >>> 32));
       result = 31 * result + (type != null ? type.hashCode() : 0);
       result = 31 * result + (color != null ? color.hashCode() : 0);
       result = 31 * result + (country != null ? country.hashCode() : 0);
       result = 31 * result + (numberPlate != null ? numberPlate.hashCode() : 0);
       return result;
    }
}