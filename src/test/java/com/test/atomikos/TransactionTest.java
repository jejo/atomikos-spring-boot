package com.test.atomikos;

import com.test.atomikos.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jdimayuga on 30/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class TransactionTest {
    @Autowired
    private TransactionService transactionService;

    @Test
    public void testSave() {
        transactionService.save();

    }
}
