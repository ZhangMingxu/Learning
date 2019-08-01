package com.xufree.learning.java.future;

import java.util.concurrent.*;

/**
 * 异步测试
 *
 * @author zhangmingxu ON 11:28 2019-08-01
 **/
public class FutureInAction {
    private static final ExecutorService pool = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("线程: " + Thread.currentThread().getName() + " getFuture 开始调用");
        Future<String> future = getFuture();
        System.out.println("线程: " + Thread.currentThread().getName() + " 开始处理其他逻辑");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("线程: " + Thread.currentThread().getName() + " 处理其他逻辑结束");
        while (true) {
            boolean done = future.isDone();
            System.out.println("线程: " + Thread.currentThread().getName() + " 获取是否完成结果 " + done);
            if (done) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(800);
        }
        System.out.println("线程: " + Thread.currentThread().getName() + " getFuture 返回结果:" + future.get());
        pool.shutdown();
    }

    private static Future<String> getFuture() {
        Callable<String> callable = () -> {
            System.out.println("线程: " + Thread.currentThread().getName() + " getFuture 开始处理");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("线程: " + Thread.currentThread().getName() + " getFuture 处理结束");
            return "getFuture done";
        };
        return pool.submit(callable);
    }
}
