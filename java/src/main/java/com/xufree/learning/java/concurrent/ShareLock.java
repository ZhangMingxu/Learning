package com.xufree.learning.java.concurrent;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ShareLock implements Lock {
    private Sync sync;

    public ShareLock(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("share count must large than zero!");
        }
        sync = new Sync(count);
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public Collection<Thread> getQueuedThreads() {
        return sync.getQueuedThreads();
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            setState(count);
        }


        @Override
        protected int tryAcquireShared(int arg) {
            int current = getState();
            int newCount = current - arg;
            if (newCount < 0 || compareAndSetState(current, newCount)) {
                return newCount;
            }
            return -1;
        }


        @Override
        protected boolean tryReleaseShared(int arg) {
            int current = getState();
            int newCount = current + arg;
            return compareAndSetState(current, newCount);
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

    }
}
