package com.yulintu.business.configurations;

import com.yulintu.thematic.data.DefaultTransactionStatus;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultTransactionManager implements PlatformTransactionManager {


    //region ctor
    public DefaultTransactionManager() {
    }
    //endregion

    //region methods
    @Override
    public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {

        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.setActualTransactionActive(true);
            TransactionSynchronizationManager.setCurrentTransactionIsolationLevel(-1);
            TransactionSynchronizationManager.setCurrentTransactionReadOnly(false);
            TransactionSynchronizationManager.setCurrentTransactionName("Unknown");
            TransactionSynchronizationManager.initSynchronization();
            TransactionSynchronizationManager.bindResource("counter", new AtomicInteger());
        }

        AtomicInteger counter = (AtomicInteger) TransactionSynchronizationManager.getResource("counter");
        counter.incrementAndGet();
        return new DefaultTransactionStatus();
    }

    @Override
    public void commit(TransactionStatus transactionStatus) throws TransactionException {

        AtomicInteger counter = (AtomicInteger) TransactionSynchronizationManager.getResource("counter");
        int i = counter.decrementAndGet();
        if (i == 0) {
            TransactionSynchronizationManager.unbindResource("counter");
            TransactionSynchronizationManager.clearSynchronization();
            TransactionSynchronizationManager.clear();
        }
    }

    @Override
    public void rollback(TransactionStatus transactionStatus) throws TransactionException {

        AtomicInteger counter = (AtomicInteger) TransactionSynchronizationManager.getResource("counter");
        int i = counter.decrementAndGet();
        if (i == 0) {
            TransactionSynchronizationManager.unbindResource("counter");
            TransactionSynchronizationManager.clearSynchronization();
            TransactionSynchronizationManager.clear();
        }
    }
    //endregion
}
