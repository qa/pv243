package cz.muni.fi.pv243.lesson02.factorial;

import java.math.BigInteger;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class MathOperations {

    /**
     * Returns a result of multiplication of a sequence of (from * (from + 1) * (from + 2) ... (to -1) * to)
     * 
     * @throws IllegalArgumentException if any of the parameters is less than or equal to zero or if the "to" parameter is less
     *         then the "from" parameter
     */
    public BigInteger multiplySequence(long from, long to) {
        if (from <= 0 || to <= 0) {
            throw new IllegalArgumentException("Must be positive");
        }
        if (from > to) {
            throw new IllegalArgumentException("from must be lower than to");
        }

        BigInteger result = BigInteger.ONE;
        for (long i = from; i <= to; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

    @Asynchronous
    public Future<BigInteger> multiplySequenceAsync(long from, long to) {
        return new AsyncResult<BigInteger>(multiplySequence(from, to));
    }
}
