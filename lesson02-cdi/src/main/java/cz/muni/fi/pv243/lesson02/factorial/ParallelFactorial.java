package cz.muni.fi.pv243.lesson02.factorial;

import java.math.BigInteger;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Implementation of factorial that is capable of computing factorial in two threads by splitting the range into two parts.
 * 
 * @author Jozef Hartinger
 * 
 */
@Parallel
@ApplicationScoped
@Named
public class ParallelFactorial extends FactorialImpl {

    @Inject
    private MathOperations math;

    @Override
    public BigInteger compute(long number) {
        if (number > 2) {
            long part = number / 2;
            Future<BigInteger> firstPart = math.multiplySequenceAsync(1, part);
            Future<BigInteger> secondPart = math.multiplySequenceAsync(part + 1, number);
            try {
                return fireEvent(number, firstPart.get().multiply(secondPart.get()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return super.compute(number);
        }
    }
}