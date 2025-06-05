package com.example.taskmanager.logging;

// LoggingService.java
public interface LoggingService {
    void info(String message, Object... args);
    void error(String message, Throwable throwable, Object... args);
    void debug(String message, Object... args);
}

