package cz.muni.fi.pv243.lesson03.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.picketlink.idm.api.User;

@Entity
public class Manager implements User, Serializable
{
   private String username;

   private String password;
   
   private boolean admin;

   public Manager() {}
   
   public Manager(String username, String password, boolean admin)
   {
      this.username = username;
      this.password = password;
      this.admin = admin;
   }

   @Id
   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   @Override
   @Transient
   public String getKey()
   {
      return getId();
   }

   @Override
   @Transient
   public String getId()
   {
      return username;
   }

   public boolean isAdmin()
   {
      return admin;
   }

   public void setAdmin(boolean admin)
   {
      this.admin = admin;
   }
}
