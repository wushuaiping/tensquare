package io.wooo.tensquare.user.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class SnowFlake {

    /**
     * 起始的时间戳
     */
    private static final long START_STAMP = 1262275200000L;

    /**
     * 每一部分占用的位数
     */
    //序列号占用的位数
    private static final long SEQUENCE_BIT = 12;
    //机器标识占用的位数
    private static final long MACHINE_BIT = 10;

    /**
     * 每一部分的最大值
     */
    public static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    //机器标识
    private long machineId;
    //序列号
    private long sequence = 0L;
    //上一次时间戳
    private long lastStamp = -1L;

    SnowFlake(long machineId) {

        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     */
    synchronized Long nextId() {
        long currStamp = getNewStamp();
        if (currStamp < lastStamp) {
            throw new TimeBackwardsException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStamp == lastStamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;

            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                sequence = new Random().nextInt(10);
                currStamp = getNextMill();
            }
        } else {
            // 新的一毫秒，随机从 0-9 中开始
            sequence = new Random().nextInt(10);
        }
        lastStamp = currStamp;

        return (currStamp - START_STAMP) << TIMESTAMP_LEFT //时间戳部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }

        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

    private static class TimeBackwardsException extends RuntimeException {
        TimeBackwardsException(String message) {
            super(message);
        }
    }

}
