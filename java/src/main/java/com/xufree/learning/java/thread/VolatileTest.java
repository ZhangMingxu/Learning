public class VolatileTest {

    public static boolean stop ;

    public static void doSomething(){
        System.out.println("stop正在执行!");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(){
            @Override
            public void run(){
                stop = false;
                while (!stop){
                    doSomething();
                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run(){
                stop = true;
                System.out.println("thread2:stop:"+stop);
            }
        };
        thread1.start();
        thread2.start();
    }
}