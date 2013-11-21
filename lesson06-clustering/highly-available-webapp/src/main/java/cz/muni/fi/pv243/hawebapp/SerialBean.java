package cz.muni.fi.pv243.hawebapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Logger;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

import org.wildfly.clustering.annotation.Immutable;

@Immutable
public class SerialBean implements Serializable, HttpSessionActivationListener {

    private int serial;
    private byte[] cargo;
    private transient Logger logger = Logger.getLogger(SerialBean.class.getName());

    public SerialBean() {
        this.serial = 0;
        // Feed random data :-)
        this.cargo = new byte[4 * 1024];
        Random rand = new Random();
        rand.nextBytes(cargo);
    }

    public byte[] getCargo() {
        return cargo;
    }

    public void setCargo(byte[] cargo) {
        this.cargo = cargo;
    }

    public int getSerial() {
        return serial;
    }

    public int getSerialAndIncrement() {
        int retVal = this.getSerial();
        serial++;
        return retVal;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    /**
     * @see http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     * @param stream
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        logger.info("Writing session.");
    }

    /**
     * @see http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     * @param stream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        logger = Logger.getLogger(SerialBean.class.getName());
        logger.info("Reading session.");
    }

    /**
     * @see http://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSessionActivationListener.html
     * @param se
     */
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        logger.info("Session will passivate.");
    }

    /**
     * @see http://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSessionActivationListener.html
     * @param se
     */
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        logger.info("Session activated.");
    }
}
