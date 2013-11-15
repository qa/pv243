package cz.muni.fi.pv243.lesson02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigInteger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv243.lesson02.factorial.Factorial;
import cz.muni.fi.pv243.lesson02.factorial.Parallel;
import cz.muni.fi.pv243.lesson02.factorial.util.FactorialComputationFinished;

@RunWith(Arquillian.class)
public class FactorialTest {

    @Inject
    @Parallel
    private Factorial factorial;

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"), "beans.xml")
                .addPackages(true, Factorial.class.getPackage()).addClass(EventObserver.class);
    }

    @Test
    public void testSerialFactorial() {
        assertEquals(BigInteger.ONE, factorial.compute(0));
        assertEquals(BigInteger.ONE, factorial.compute(1));
        assertEquals(BigInteger.valueOf(2), factorial.compute(2));
        assertEquals(BigInteger.valueOf(6), factorial.compute(3));
        assertEquals(BigInteger.valueOf(24), factorial.compute(4));
        assertEquals(BigInteger.valueOf(120), factorial.compute(5));
        assertEquals(BigInteger.valueOf(3628800), factorial.compute(10));
    }

    @Test
    @Ignore // remove once event notification is implemented
    public void testEvent(EventObserver observer) {
        observer.reset();
        factorial.compute(6);
        assertNotNull(observer.getEvent());
        assertNotNull(observer.getEvent().getResult());
        assertEquals(6, observer.getEvent().getNumber());
        assertEquals(BigInteger.valueOf(720), observer.getEvent().getResult());
    }

    @ApplicationScoped
    protected static class EventObserver {
        private FactorialComputationFinished event;

        public FactorialComputationFinished getEvent() {
            return event;
        }

        public void reset() {
            this.event = null;
        }

        public void observe(@Observes FactorialComputationFinished event) {
            this.event = event;
        }
    }
}
