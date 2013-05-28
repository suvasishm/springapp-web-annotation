package org.springframework.springapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceIncrease {

	/** Logger for this class and subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private int percentage;
    
    public PriceIncrease() {}

    public void setPercentage(int i) {
        percentage = i;
        logger.info("Percentage set to " + i);
    }

    public int getPercentage() {
        return percentage;
    }

}