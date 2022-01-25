package com.hooz.elysium.util;

/**
 * Unique ID generator based on Twitter Snowflake
 *
 * @author Kidd Zhou
 * @date 2022-01-25
 */
public class SnowflakeUtils {

    /**
     * starting timestamp @ 2022-01-01 00:00:00
     */
    private static final long START_STAMP = 1640966400000L;

    /**
     * machine bits
     */
    private static final long MACHINE_BITS = 5;

    /**
     * sequence bits
     */
    private static final long SEQUENCE_BITS = 12;

    /**
     * datacenter bits
     */
    private static final long DATACENTER_BITS = 5;

    /**
     * max datacenter id
     */
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_BITS);

    /**
     * max machine id
     */
    private static final long MAX_MACHINE_ID = ~(-1L << MACHINE_BITS);

    /**
     * max sequence
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    /**
     * timestamp shift
     */
    private static final long TIMESTAMP_SHIFT = DATACENTER_BITS + MACHINE_BITS + SEQUENCE_BITS;

    /**
     * datacenter shift
     */
    private static final long DATACENTER_SHIFT = MACHINE_BITS + SEQUENCE_BITS;

    /**
     * machine shift
     */
    private static final long MACHINE_SHIFT = SEQUENCE_BITS;

    /**
     * datacenterId
     */
    private final long datacenterId;

    /**
     * machineId
     */
    private final long machineId;

    /**
     * sequence
     */
    private long sequence;

    /**
     * last timestamp
     */
    private long lastTime;

    public SnowflakeUtils(long datacenterId, long machineId) {
        if (datacenterId < 0L || datacenterId > MAX_DATACENTER_ID) {
            throw new IllegalArgumentException("invalid datacenter id");
        }
        if (machineId < 0L || machineId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException("invalid machine id");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currentTime = System.currentTimeMillis();
        // check local time
        if (currentTime < lastTime) {
            throw new IllegalStateException("invalid timestamp");
        }
        if (currentTime == lastTime) {
            // sequence++, the value may overflow once all the sequences during this time period have been allocated
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                while (currentTime == lastTime) {
                    currentTime = System.currentTimeMillis();
                }
            }
        } else {
            sequence = 0L;
        }
        lastTime = currentTime;
        return (currentTime - START_STAMP) << TIMESTAMP_SHIFT | datacenterId << DATACENTER_SHIFT | machineId << MACHINE_SHIFT | sequence;
    }

    public static void main(String[] args) {
        SnowflakeUtils snowflakeUtils = new SnowflakeUtils(1, 1);
        long uniqId = snowflakeUtils.nextId();
        System.out.println("generate unique id: " + uniqId);
    }
}
