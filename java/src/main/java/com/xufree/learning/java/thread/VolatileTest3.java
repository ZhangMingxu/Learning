import java.util.concurrent.CountDownLatch;

public class VolatileTest3 {

    public int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileTest3 test = new VolatileTest3();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {

                    test.increase();
                }
            }).start();
        }
        int count = 0;
        while (Thread.activeCount() > 5) {
            Thread.getAllStackTraces().keySet().forEach(thread -> {
                System.out.println(thread.getName() + "--" + thread.isDaemon());
            });
            System.out.println();
            //保证前面的线程都执行完
            Thread.yield();
            count++;
        }
        System.out.println(test.inc);
    }
}
