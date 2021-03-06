package com.cloudshiba.lockcount.lock;

public class UsingBlockLockProcessTwo {
    public static void main(String[] args) {
        DistributionLock lock = DistributionLockFactory.getLock("distributeLock", 30000);
        Long start = System.currentTimeMillis();
        System.out.println("process two begin to acquire lock ...., and now is "+ start);
        lock.acquire();
        try {  //acquire the lock do something quickly
            long end = System.currentTimeMillis();
            System.out.println("process two begin to do something ...., and now is " + end);
            System.out.println("process two end, spend time is " + (end -start) + "ms");
        } finally {
            lock.release();
        }
    }
}