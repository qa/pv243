package cz.muni.fi.pv243.hawebapp;

import java.io.Serializable;
import java.util.Random;

public class SerialBean implements Serializable {

    private int serial;
    private byte[] cargo;

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
}
