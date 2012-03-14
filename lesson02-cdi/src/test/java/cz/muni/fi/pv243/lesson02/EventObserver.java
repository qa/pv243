package cz.muni.fi.pv243.lesson02;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import cz.muni.fi.pv243.lesson02.factorial.util.FactorialComputationFinished;

@ApplicationScoped
public class EventObserver {
    private FactorialComputationFinished event;

    @SuppressWarnings("unused")
    private void observe(@Observes FactorialComputationFinished event) {
        this.event = event;
    }

    public FactorialComputationFinished getEvent() {
        return event;
    }

    public void reset() {
        event = null;
    }
}