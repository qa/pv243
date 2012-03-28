package cz.muni.fi.pv243.lesson03.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Bakery implements Serializable
{
   private Long id;

   private Long version;

   private String name;

   private List<Cake> cakes;
   
   private Manager manager;

   public Bakery()
   {
   }

   public Bakery(String name, String address, Manager manager)
   {
      this.name = name;
      this.address = address;
      this.manager = manager;
   }

   @Id
   @GeneratedValue
   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   @Version
   public Long getVersion()
   {
      return version;
   }

   public void setVersion(Long version)
   {
      this.version = version;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getAddress()
   {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   private String address;

   @OneToMany(fetch = FetchType.LAZY)
   public List<Cake> getCakes()
   {
      if (cakes == null) {
         cakes = new LinkedList<Cake>();
      }
      return cakes;
   }

   public void setCakes(List<Cake> cakes)
   {
      this.cakes = cakes;
   }

   @ManyToOne
   public Manager getManager()
   {
      return manager;
   }

   public void setManager(Manager manager)
   {
      this.manager = manager;
   }
}
