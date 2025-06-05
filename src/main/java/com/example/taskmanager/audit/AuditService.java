package com.example.taskmanager.audit;

import com.example.taskmanager.notification.Notification;

// AuditService.java
public interface AuditService {
    void recordSuccess(Notification notification);
    void recordFailure(Notification notification, Exception e);
}

