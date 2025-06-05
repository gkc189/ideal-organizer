package com.example.taskmanager.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// LoggingServiceImpl.java
@Service
public class LoggingServiceImpl implements LoggingService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingServiceImpl.class);

    @Override
    public void info(String message, Object... args) {
        logger.info(message, args);
    }

    @Override
    public void error(String message, Throwable throwable, Object... args) {
        logger.error(String.format(message, args), throwable);
    }

    @Override
    public void debug(String message, Object... args) {
        logger.debug(message, args);
    }
}

