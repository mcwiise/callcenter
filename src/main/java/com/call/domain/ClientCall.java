package com.call.domain;


import com.call.dispatcher.Dispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.function.Supplier;

public class ClientCall implements Supplier<Agent> {

    private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);
    private Agent agent;

    @Override
    public Agent get(){
        try {
            logger.info("Attending call");
            int time = mockCallTime();
            Thread.sleep(time);
            logger.info("Time on call: {}", time);
            logger.info("Hanging call out.");
        } catch (InterruptedException e) {
            logger.error("There was InterruptedException when attending the call. ", e);
        }
        return agent;
    }

    private static int mockCallTime() {
        Random ran = new Random();
        return (ran.nextInt(6) + 5)*1000;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
