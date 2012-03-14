package cz.muni.fi.pv243.lesson02.factorial;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;

import cz.muni.fi.pv243.lesson02.factorial.util.FactorialComputationFinished;

/**
 * Cache for results of factorial operation. The cache is updated upon delivery of the {@link FactorialComputationFinished}
 * event. The cache state is guarded by read/write lock (EJB).
 *
 * @author Jozef Hartinger
 *
 */
@Singleton
public class FactorialCache {
    private final Map<Long, BigInteger> cachedResults = new HashMap<Long, BigInteger>();

    @Lock(LockType.READ)
    public BigInteger get(long number) {
        return cachedResults.get(number);
    }

    @Lock(LockType.WRITE)
    public void cacheResult(@Observes FactorialComputationFinished result) {
        cachedResults.put(result.getNumber(), result.getResult());
    }

    @Lock(LockType.WRITE)
    public void clear() {
        cachedResults.clear();
    }
}
