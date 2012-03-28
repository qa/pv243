package cz.muni.fi.pv243.lesson03.security;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.jboss.seam.security.annotations.SecurityBindingType;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SecurityBindingType
@Target(
{TYPE, METHOD, PARAMETER, FIELD})
@Retention(RUNTIME)
public @interface Admin {

}
