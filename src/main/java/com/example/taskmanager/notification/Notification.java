package com.example.taskmanager.notification;

// Notification.java
public class Notification {
    private String id;
    private String recipient;
    private String content;
    private boolean shouldFail;

    // Getters, setters, constructors omitted for brevity

    public String getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public boolean shouldFail() {
        return shouldFail;
    }
}

