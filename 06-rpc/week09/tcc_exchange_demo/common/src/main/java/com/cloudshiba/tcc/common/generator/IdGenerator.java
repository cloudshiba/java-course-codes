package com.cloudshiba.tcc.common.generator;

public class IdGenerator {
    // 系統上線時間
    private final long startTime = 1601256017000L;
    // 機器 ID
    private long workId;
    // 序列號
    private long serialNum = 0;
    // 得到左移位
    private final long serialNumBits = 16L;
    private final long workIdBits = 6L;
    private final long workIdShift = serialNumBits;
    private final long timestampShift = workIdShift + workIdBits;
    private long lastTimeStamp = 0L;
    private long serialNumMax = -1 ^ (-1L << serialNumBits);

    public IdGenerator() {
        this(1L);
    }

    public IdGenerator(long workId) {
        this.workId = workId;
    }

    public synchronized long getId() {
        long timestamp = System.currentTimeMillis();
        if( timestamp == lastTimeStamp) {
            serialNum = (serialNum + 1) & serialNumMax;
            if (serialNum == 0) {
                timestamp = waitNextMillis(timestamp);
            }
        } else {
            serialNum = timestamp & 1;
        }
        lastTimeStamp = timestamp;
        return ((timestamp - startTime) << timestampShift)
                | (workId << workIdShift)
                | serialNum;
    }

    private long waitNextMillis(final long timestamp) {
        long nowTimestamp = System.currentTimeMillis();
        while ( timestamp >= nowTimestamp) {
            nowTimestamp = System.currentTimeMillis();
        }
        return nowTimestamp;
    }
}
