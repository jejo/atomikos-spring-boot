package com.test.atomikos.config;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Created by jdimayuga on 30/08/2017.
 */
public class AtomikosJtaPlatform extends AbstractJtaPlatform {

    private static UserTransaction ut;
    private static TransactionManager tm;

    @Override
    protected TransactionManager locateTransactionManager() {
        return tm;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return ut;
    }

    public UserTransaction getUt() {
        return ut;
    }

    public void setUt(UserTransaction ut) {
        AtomikosJtaPlatform.ut = ut;
    }

    public TransactionManager getTm() {
        return tm;
    }

    public void setTm(TransactionManager tm) {
        AtomikosJtaPlatform.tm = tm;
    }
}
