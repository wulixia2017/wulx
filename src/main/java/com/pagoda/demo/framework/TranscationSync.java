package com.pagoda.demo.framework;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;

public class TranscationSync extends TransactionSynchronizationAdapter {

    public void afterCommit(){
        System.out.println("测试");
    }
}
