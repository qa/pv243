package cz.muni.fi.pv243.lesson03.model;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.seam.persistence.FlushModeManager;
import org.jboss.seam.persistence.FlushModeType;


@Singleton
@Startup
public class FlushMode
{
   @Inject
   FlushModeManager flushModeManager;
   
   @PostConstruct
   public void initialize()
   {
      flushModeManager.setFlushModeType(FlushModeType.MANUAL);
   }
}
