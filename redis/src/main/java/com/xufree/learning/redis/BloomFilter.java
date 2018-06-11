package com.xufree.learning.redis;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;

public class BloomFilter {
    @Resource
    private JedisPool jedisPool;
    //预计插入量
    private long expectedInsertions = 100000000;
    //可接受的错误率
    private double fpp = 0.001F;
    private String redisKeyPrefix = "bf:";

    public void setExpectedInsertions(long expectedInsertions) {
        this.expectedInsertions = expectedInsertions;
    }

    public void setFpp(double fpp) {
        this.fpp = fpp;
    }

    public void setRedisKeyPrefix(String redisKeyPrefix) {
        this.redisKeyPrefix = redisKeyPrefix;
    }

    //bit数组长度
    private long numBits = optimalNumOfBits(expectedInsertions, fpp);
    //hash函数数量
    private int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);

    //计算hash函数个数
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / (double) n * Math.log(2.0D)));
    }

    //计算bit数组长度
    private long optimalNumOfBits(long n, double p) {
        if (p == 0.0D) {
            p = 4.9E-324D;
        }
        return (long) ((double) (-n) * Math.log(p) / (Math.log(2.0D) * Math.log(2.0D)));
    }

    /**
     * 判断keys是否存在于集合
     */
    public boolean isExist(String where, String key) {
        long[] indexs = getIndexs(key);
        boolean result;
        Pipeline pipeline = jedisPool.getResource().pipelined();
        try {
            for (long index : indexs) {
                pipeline.getbit(getRedisKey(where), index);
            }
            result = !pipeline.exec().get().contains(false);
        } finally {
            try {
                pipeline.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (result) {
            put(where, key);
        }
        return result;
    }

    /**
     * 将key存入redis bitmap
     */
    private void put(String where, String key) {
        long[] indexs = getIndexs(key);
        Pipeline pipeline = jedisPool.getResource().pipelined();
        try {
            for (long index : indexs) {
                pipeline.setbit(getRedisKey(where), index, true);
            }
            pipeline.exec();
        } finally {
            try {
                pipeline.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据key获取bitmap下标
     */
    private long[] getIndexs(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    /**
     * 获取一个hash值
     */
    private long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
    }

    private String getRedisKey(String where) {
        return redisKeyPrefix + where;
    }
}
