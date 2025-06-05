package com.example.taskmanager.notification;

import java.util.List;

// NotificationService.java
public interface NotificationService {
    void send(Notification notification);
    void sendAll(List<Notification> notifications);
}

