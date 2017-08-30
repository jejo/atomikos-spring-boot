package com.test.atomikos.service.impl;

import com.test.atomikos.model.ds1.TableDs1;
import com.test.atomikos.model.ds2.TableDs2;
import com.test.atomikos.repo.ds1.TableDs1Repository;
import com.test.atomikos.repo.ds2.TableDs2Repository;
import com.test.atomikos.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;

/**
 * Created by jdimayuga on 30/08/2017.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TableDs1Repository tableDs1Repository;

    @Autowired
    private TableDs2Repository tableDs2Repository;

    @Transactional
    @Override
    public void save() {

        TableDs1 tableDs1 = new TableDs1();

        tableDs1.setName("Five");
        tableDs1Repository.save(tableDs1);



        TableDs2 tableDs2 = new TableDs2();


        tableDs2.setName("Five");



        tableDs2Repository.save(tableDs2);
        throw new RuntimeException();


    }


}
