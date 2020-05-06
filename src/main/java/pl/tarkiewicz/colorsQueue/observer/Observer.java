package pl.tarkiewicz.colorsQueue.observer;

import java.util.logging.Logger;

import javax.inject.Singleton;

import pl.tarkiewicz.colorsQueue.validation.RequestValidation;

@Singleton
public class Observer implements MyObserver {

    private Logger logger;

    public Observer() {
        logger = Logger.getLogger(RequestValidation.class.getName());
    }

    @Override
    public void finish(String info) {
        logger.info(String.format("Event %s has been sent to the queue", info));
    }
}
