package com.example.demo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class LoggingAuditEventListener {
    @EventListener
    public void on(AuditApplicationEvent event) {
        log.error("An Audit Event was received: {}", event);
    }
}