package com.xufree.learning.java.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangmingxu ON 16:43 2019-07-11
 **/
public class PrintNumber {
    public static void main(String[] args) throws InterruptedException {
        printA();
//        printB();
//        printC();
//        printD();
//        printF();
    }

    /**
     * 3个线程，打印分别有自己的数组，内含不同元素，多线程分别按顺序交替轮流打印3个数组中的元素
     * [A1,A2,A3]
     * [B1,B2,B3]
     * [C1,C2,C3]
     * <p>
     * 输出：
     * A1,B1,C2,A2,B2,C2,A3,B3,C3
     */
    private static void printA() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch count = new CountDownLatch(3);
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        new Thread(() -> {
            String[] array = {"A1", "A2", "A3"};
            int i = 0;
            while (true) {
                if (i == array.length) {
                    break;
                }
                lock.lock();
                try {
                    a.await();
                    System.out.println(array[i++]);
                    b.signal();
                } catch (InterruptedException ignored) {

                } finally {
                    lock.unlock();
                }
            }
            count.countDown();
        }, "A").start();
        new Thread(() -> {
            String[] array = {"B1", "B2", "B3"};
            int i = 0;
            while (true) {
                if (i == array.length) {
                    break;
                }
                lock.lock();
                try {
                    b.await();
                    System.out.println(array[i++]);
                    c.signal();
                } catch (InterruptedException ignored) {

                } finally {
                    lock.unlock();
                }
            }
            count.countDown();
        }, "B").start();
        new Thread(() -> {
            String[] array = {"C1", "C2", "C3"};
            int i = 0;
            while (true) {
                if (i == array.length) {
                    break;
                }
                lock.lock();
                try {
                    c.await();
                    System.out.println(array[i++]);
                    a.signal();
                } catch (InterruptedException ignored) {

                } finally {
                    lock.unlock();
                }
            }
            count.countDown();
        }, "C").start();
        lock.lock();
        try {
            a.signal();
        } finally {
            lock.unlock();
        }
        count.await();
    }

    /**
     * 三个线程，打印同一个数组，按顺序轮流
     */
    private static void printB() throws InterruptedException {
        int[] array = new int[500];
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch count = new CountDownLatch(3);
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        final AtomicInteger i = new AtomicInteger(0);

        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        a.await();
                        if (i.get() < array.length) {
                            System.out.println("A" + array[i.getAndIncrement()]);
                            b.signal();
                        } else {
                            b.signal();
                            break;
                        }
                    } catch (InterruptedException ignored) {

                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                count.countDown();
            }

        }, "A").start();
        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        b.await();
                        if (i.get() < array.length) {
                            System.out.println("B" + array[i.getAndIncrement()]);
                            c.signal();
                        } else {
                            c.signal();
                            break;
                        }
                    } catch (InterruptedException ignored) {

                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                count.countDown();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        c.await();
                        if (i.get() < array.length) {
                            System.out.println("C" + array[i.getAndIncrement()]);
                            a.signal();
                        } else {
                            a.signal();
                            break;
                        }
                    } catch (InterruptedException ignored) {

                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                count.countDown();
            }
        }, "C").start();
        lock.lock();
        try {
            a.signal();
        } finally {
            lock.unlock();
        }
        count.await();
    }

    /**
     * 3个线程，打印分别有自己的数组，内含不同元素，多线程分别按顺序交替轮流打印3个数组中的元素
     * [A1,A2,A3]
     * [B1,B2,B3]
     * [C1,C2,C3]
     * 和A的区别是支持不等长数组
     * <p>
     * 输出：
     * A1,B1,C2,A2,B2,C2,A3,B3,C3
     */
    private static void printC() {
        int[] arrayA = {1, 2, 3};
        int[] arrayB = {1, 2, 3, 4, 5, 6, 7};
        int[] arrayC = {1, 2, 3, 4, 5, 6};
        LinkedList<WorkerThread> list = new LinkedList<>();
        Lock lock = new ReentrantLock();
        Condition mainCondition = lock.newCondition();
        WorkerThread threadA = new WorkerThread(list, lock, lock.newCondition(), mainCondition, arrayA, "A");
        WorkerThread threadB = new WorkerThread(list, lock, lock.newCondition(), mainCondition, arrayB, "B");
        WorkerThread threadC = new WorkerThread(list, lock, lock.newCondition(), mainCondition, arrayC, "C");
        new Thread(threadA, "A").start();
        new Thread(threadB, "B").start();
        new Thread(threadC, "C").start();
        list.add(threadA);
        list.add(threadB);
        list.add(threadC);
        WorkerThread cur = list.poll();
        while (true) {
            lock.lock();
            try {
                if (cur == null) {
                    break;
                }
                cur.condition.signal();
                cur = list.poll();
                mainCondition.await();
            } catch (InterruptedException ignored) {

            } finally {
                lock.unlock();
            }
        }
        //通知最后一次输出退出循环
        lock.lock();
        try {
            threadA.condition.signal();
            threadB.condition.signal();
            threadC.condition.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 三个线程，打印同一个数组，按顺序轮流
     * 和B的区别是使用一个Object对象进行通知
     */
    private static void printD() {
        Object l = new Object();
        int[] array = new int[500];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        final AtomicInteger i = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (l) {
                    try {
                        l.wait();
                        int x = i.get();
                        if (x == array.length) {
                            l.notifyAll();
                            break;
                        }
                        if (x % 3 == 0) {
                            System.out.println("线程t1==>A" + array[i.getAndIncrement()]);
                        }
                        l.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (l) {
                    try {
                        l.wait();
                        int x = i.get();
                        if (x == array.length) {
                            l.notifyAll();
                            break;
                        }
                        if (x % 3 == 1) {
                            System.out.println("线程t2==>B" + array[i.getAndIncrement()]);
                        }
                        l.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                synchronized (l) {
                    try {
                        l.wait();
                        int x = i.get();
                        if (x == array.length) {
                            l.notifyAll();
                            break;
                        }
                        if (x % 3 == 2) {
                            System.out.println("线程t3==>C" + array[i.getAndIncrement()]);
                        }
                        l.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        t1.start();
        t2.start();
        t3.start();
        while (true) {
            if (t1.getState().equals(Thread.State.WAITING)) {
                notify(l, "l");
                return;
            }
        }
    }

    /**
     * 三个线程，打印同一个数组，按顺序轮流
     * 和B的区别是使用三个Object对象进行通知
     */
    private static void printF() {
        Object l1 = new Object();
        Object l2 = new Object();
        Object l3 = new Object();
        int[] array = new int[500];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        final AtomicInteger i = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {

            while (true) {
                synchronized (l1) {
                    try {
                        l1.wait();
                        if (i.get() < array.length) {
                            System.out.println("A" + array[i.getAndIncrement()]);
                            notify(l2, "l2");
                        } else {
                            notify(l2, "l2");
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (l2) {
                    try {
                        l2.wait();
                        if (i.get() < array.length) {
                            notify(l3, "l3");
                        } else {
                            notify(l3, "l3");
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                synchronized (l3) {
                    try {
                        l3.wait();
                        if (i.get() < array.length) {
                            System.out.println("C" + array[i.getAndIncrement()]);
                            notify(l1, "l1");
                        } else {
                            notify(l1, "l1");
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        t1.start();
        t2.start();
        t3.start();
        while (true) {
            if (t1.getState().equals(Thread.State.WAITING)) {
                notify(l1, "l1");
                return;
            }
        }
    }

    private static void notify(Object lock, String name) {
        synchronized (lock) {
            lock.notify();
        }
    }

    private static class WorkerThread implements Runnable {
        private List<WorkerThread> list;
        private Lock lock;
        private Condition condition;
        private Condition main;
        private int[] array;
        private String name;

        public WorkerThread(List<WorkerThread> list, Lock lock, Condition condition, Condition main, int[] array, String name) {
            this.list = list;
            this.lock = lock;
            this.condition = condition;
            this.main = main;
            this.array = array;
            this.name = name;
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                lock.lock();
                try {
                    condition.await();
                    if (i >= array.length) {
                        main.signal();
                        break;
                    }
                    System.out.println(name + " " + array[i++]);
                    list.add(this);
                    main.signal();
                } catch (InterruptedException ignored) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
