package cz.muni.fi.pv243.lesson02.factorial;

import java.math.BigInteger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("factorial")
public class FactorialImpl implements Factorial {

    @Inject
    private MathOperations math;

    @Override
    public BigInteger compute(long number) {
        BigInteger result = null;
        if (number == 0) {
            result = BigInteger.ONE;
        } else {
            result = math.multiplySequence(1, number);
        }
        return result;
    }
}
