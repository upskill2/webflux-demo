package com.webflux.user_service.configs;

import com.webflux.user_service.entity.UserTransaction;
import org.springframework.context.event.EventListener;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserTransactionEventListener {

    @EventListener
    public void handleBeforeSave(BeforeSaveEvent<UserTransaction> event) {
        UserTransaction userTransaction = event.getEntity();
        if (userTransaction.getTransactionTime() == null) {
            userTransaction.setTransactionTime(LocalDateTime.now());
        }
    }
}