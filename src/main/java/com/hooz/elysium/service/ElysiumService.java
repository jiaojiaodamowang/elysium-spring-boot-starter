package com.hooz.elysium.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * Elysium main service
 *
 * @author Kidd Zhou
 * @date 2022-01-01
 */
public class ElysiumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElysiumService.class);

    public String echo() {
        return "Welcome to use Elysium!";
    }

    @PostConstruct
    public void init() {
        LOGGER.info("Elysium service has been registered...");
    }
}
