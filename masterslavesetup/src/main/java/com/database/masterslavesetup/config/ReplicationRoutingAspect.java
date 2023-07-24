package com.database.masterslavesetup.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/*
* aspect로 메서드를 가로채 read인지 write인지 결정하는 기능 클래스
* */
@Aspect
@Component
public class ReplicationRoutingAspect {

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void setReadOrWriteDataSource(JoinPoint joinPoint) {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            ReplicationContextHolder.setDataSourceType(DataSourceType.SLAVE);
        } else {
            ReplicationContextHolder.setDataSourceType(DataSourceType.MASTER);
        }
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void clearDataSourceType() {
        ReplicationContextHolder.clearDataSourceType();
    }
}
