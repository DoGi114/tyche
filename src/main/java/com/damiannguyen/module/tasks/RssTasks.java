package com.damiannguyen.module.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RssTasks {
    private final static Logger LOGGER = LoggerFactory.getLogger(RssTasks.class);

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void getRss(){
        LOGGER.info("Get RSS{}", DATE_FORMAT.format(new Date()));
    }
}
