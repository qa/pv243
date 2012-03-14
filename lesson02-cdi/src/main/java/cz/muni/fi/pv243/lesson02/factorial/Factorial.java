package cz.muni.fi.pv243.lesson02.factorial;

import java.math.BigInteger;

public interface Factorial {

    /**
     * Computes the factorial of the given number.
     * @param number
     * @return factorial of the given number
     */
    BigInteger compute(long number);

}