package com.damiannguyen.module.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ZipPackageTasks {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ZipPackageTasks.class);
    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 10000)
    public void getRss() {
        LOGGER.info("ZipPackage {}", dateFormat.format(new Date()));
    }
}