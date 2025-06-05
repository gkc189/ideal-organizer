package com.example.taskmanager.notification;

import org.springframework.stereotype.Component;

@Component
public class NotificationSelfInvoker {

    private final NotificationServiceImpl proxy;

    public NotificationSelfInvoker(NotificationServiceImpl proxy) {
        this.proxy = proxy;
    }

    public void send(Notification notification) {
        proxy.send(notification);
    }
}

