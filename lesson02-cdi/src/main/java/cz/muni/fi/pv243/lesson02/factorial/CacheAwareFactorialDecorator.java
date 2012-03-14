package cz.muni.fi.pv243.lesson02.factorial;

import java.math.BigInteger;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

/**
 * Decorator that checks the {@link FactorialCache} before initiating computation. If the result is found in cache, it is
 * returned.
 *
 * @author Jozef Hartinger
 *
 */
@Decorator
public class CacheAwareFactorialDecorator implements Factorial {

    @Inject
    @Delegate
    @Any
    private Factorial delegate;

    @Inject
    private FactorialCache cache;

    @Override
    public BigInteger compute(long number) {
        BigInteger result = cache.get(number);
        if (result == null) {
            result = delegate.compute(number);
        }
        return result;
    }
}
