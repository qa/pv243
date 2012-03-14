package cz.muni.fi.pv243.lesson02.factorial.util;

import java.math.BigInteger;
import java.util.Arrays;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converts a BigInteger to its byte representation.
 *
 * @author Jozef Hartinger
 *
 */
@FacesConverter("bigIntegerByteArray")
public class BigIntegerToByteArrayConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return BigInteger.valueOf(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof BigInteger)) {
            throw new IllegalArgumentException("Expected an instance of BigInteger but received: " + value);
        }
        return Arrays.toString(BigInteger.class.cast(value).toByteArray());
    }

}
