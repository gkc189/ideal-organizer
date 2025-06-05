package com.example.taskmanager.notification;

import org.springframework.stereotype.Component;

@Component
public class NotificationSender {
    public void send(Notification notification) {
        // Simulate notification send logic
        if (notification.shouldFail()) {
            throw new RuntimeException("Simulated send failure");
        }
    }
}

