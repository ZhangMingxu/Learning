package com.xufree.learning.java.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本地缓存
 * 采用懒过期模式 在查询时才判断是否过期
 * 在缓存满了的时候触发主动过期过期
 *
 * @author zhangmingxu ON 17:52 2019-05-20
 **/
public class LocalCache {
    private static final Logger logger = LoggerFactory.getLogger(LocalCache.class);
    private static final int DEFAULT_MAX_NUMBER = 100; //默认最大缓存对象数

    private final Map<String, Value> cache;
    private final int maxNumber;
    private final AtomicInteger cur = new AtomicInteger(0);

    /**
     * 使用默认最大对象数100
     */
    public LocalCache() {
        this(DEFAULT_MAX_NUMBER);
    }

    public LocalCache(int maxNumber) {
        this.maxNumber = maxNumber;
        this.cache = new ConcurrentHashMap<>(maxNumber);
    }

    /**
     * 添加
     * 判断是否超过最大限制 如果超过触发一次全量过期
     * 如果全量过期后还不够返回false
     *
     * @param key    对应的key
     * @param value  值
     * @param expire 过期时间 单位毫秒
     */
    public boolean put(String key, Object value, long expire) {
        if (StringUtils.isBlank(key) || value == null || expire < 0) {
            logger.error("本地缓存put参数异常");
            return false;
        }
        if (!incr()) {
            return false;
        }
        if (isOver()) { //判断是否需要过期
            expireAll(); //触发一次全量过期
            if (isOver()) { //二次检查
                logger.error("本地缓存put时全量过期后还没有空间");
                decr();
                return false;
            }
        }
        putValue(key, value, expire);
        return true;
    }

    /**
     * 获取时判断过期时间
     * 在这里实现懒过期
     */
    public Object get(String key) {
        Value v = cache.get(key);
        if (v == null) {
            return null;
        }
        if (isExpired(v)) {
            logger.info("本地缓存key={}已经过期", key);
            removeValue(key);
            return null;
        }
        return v.value;
    }

    private boolean isExpired(Value v) {
        long current = System.currentTimeMillis();
        return current - v.updateTime > v.expire;
    }

    /**
     * 扫描所有的对象对需要过期的过期
     */
    private void expireAll() {
        logger.info("开始过期本地缓存");
        for (Map.Entry<String, Value> entry : cache.entrySet()) {
            if (isExpired(entry.getValue())) {
                removeValue(entry.getKey());
            }
        }
    }

    private void putValue(String key, Object value, long expire) {
        Value v = new Value(System.currentTimeMillis(), expire, value);
        if (cache.put(key, v) != null) {//存在覆盖 使得cur和map的size统一
            decr();
        }
    }

    private void removeValue(String key) {
        if (cache.remove(key) != null) { //真正删除成功了  使得cur和map的size统一
            decr();
        }
    }


    private boolean isOver() {
        return cur.get() > maxNumber;
    }

    private boolean incr() {
        int c = cur.get();
        return cur.compareAndSet(c, ++c);
    }

    private void decr() {
        for (; ; ) {
            int c = cur.get();
            if (c == 0) {
                logger.error("LocalCache decr cur is 0");
                return;
            }
            if (cur.compareAndSet(c, --c)) {
                return;
            }
        }
    }

    private static class Value {
        private long updateTime;
        private long expire;
        private Object value;

        private Value(long updateTime, long expire, Object value) {
            this.updateTime = updateTime;
            this.expire = expire;
            this.value = value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        LocalCache localCache = new LocalCache();
        int n = 500; //线程数
        int m = 100000; //每个线程put个数
        CountDownLatch count = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                for (int j = 0; j < m; j++) {
                    localCache.put(j + "", new Object(), 10);
                }
                count.countDown();
            }).start();
        }
        count.await();
        System.out.println("size:" + localCache.cache.size());
        System.out.println("cur:" + localCache.cur);
        System.out.println("耗时  " + (System.currentTimeMillis() - start));
    }
}
