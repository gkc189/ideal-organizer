package com.example.taskmanager.notification;

import com.example.taskmanager.audit.AuditService;
import com.example.taskmanager.logging.LoggingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

// NotificationServiceImpl.java
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationSender sender;
    private final AuditService auditService;
    private final NotificationSelfInvoker selfInvoker;
    private final LoggingService loggingService;

    public NotificationServiceImpl(NotificationSender sender,
                                   AuditService auditService,
                                   NotificationSelfInvoker selfInvoker,
                                   LoggingService loggingService) {
        this.sender = sender;
        this.auditService = auditService;
        this.selfInvoker = selfInvoker;
        this.loggingService = loggingService;
    }

    @Override
    public void sendAll(List<Notification> notifications) {
        loggingService.info("Starting batch send of {} notifications", notifications.size());

        for (Notification notification : notifications) {
            try {
                loggingService.debug("Dispatching notification: {}", notification.getId());
                selfInvoker.send(notification);
            } catch (Exception e) {
                loggingService.error("Failed to send notification: {}", e, notification.getId());
                auditService.recordFailure(notification, e);
            }
        }

        loggingService.info("Completed batch send of notifications");
    }

    @Transactional
    public void send(Notification notification) {
        loggingService.info("Sending notification [{}] to recipient: {}", notification.getId(), notification.getRecipient());

        try {
            sender.send(notification);
            auditService.recordSuccess(notification);
            loggingService.info("Successfully sent notification [{}]", notification.getId());
        } catch (Exception ex) {
            auditService.recordFailure(notification, ex);
            loggingService.error("Error sending notification [{}]", ex, notification.getId());
            throw ex;
        }
    }
}
