package cz.muni.fi.pv243.lesson02.factorial.view;

import java.math.BigInteger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.lesson02.factorial.Factorial;

@Named("factorial")
@RequestScoped
public class FactorialBackingBean {

    private long input;
    private BigInteger result;

    @Inject
    private Factorial factorial;

    public void compute() {
        this.result = factorial.compute(input);
    }

    public void reset() {
        this.input = 0;
        this.result = null;
    }

    public long getInput() {
        return input;
    }

    public void setInput(long input) {
        this.input = input;
    }

    public BigInteger getResult() {
        return result;
    }

    public void setResult(BigInteger result) {
        this.result = result;
    }

}
