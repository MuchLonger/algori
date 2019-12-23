import org.junit.Test;

/**
 * @description:
 * @Time: 2019/12/18 17:42
 */
public class Concurrent {
    private static volatile int ai = 1;
    private static Object lock = new Object();

    @Test
    public void c1() {
        for (int i = 0; i < 50; i++) {
            m1();
        }
    }

    public void m1() {
        Runnable rn = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    for (int i = 0; i < 50; i++) {
                        if (ai % 2 == 0) {
                            notifyAll();
                            ai++;
                            System.out.println(ai + ", " + Thread.currentThread().getName());
                        }else{
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };
        Thread t1 = new Thread(rn, "t1");
        Thread t2 = new Thread(rn, "t2");
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        Runnable rn = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    ai++;
                    System.out.println(ai + ", " + Thread.currentThread().getName());
                }
            }
        };
        Thread t1 = new Thread(rn, "t1");
        Thread t2 = new Thread(rn, "t2");
        t1.start();
        t2.start();
    }
}
