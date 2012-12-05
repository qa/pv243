package org.jboss.ee6lab.cdi.wumpus.xmlbeans;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
public @interface RoomName {
   String value();
   
   public static class RoomNameLiteral extends AnnotationLiteral<RoomName> implements RoomName {	    
	    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomNameLiteral other = (RoomNameLiteral) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

		/**
	 * 
	 */
	private static final long serialVersionUID = 3237202887991048175L;
		private String value;
	    
	    public RoomNameLiteral(String value) {
	    	this.value = value;
	    }
	    
		@Override
		public String value() {
			return value;
		}
	}

}
