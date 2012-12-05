package org.jboss.ee6lab.cdi.wumpus.xmlbeans;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class StringsProducer {
	@Produces
	@StringsEntry
	public String getString(InjectionPoint ip) {
		StringsEntry annotation = ip.getAnnotated().getAnnotation(StringsEntry.class);
		return annotation.value();
	}
}
