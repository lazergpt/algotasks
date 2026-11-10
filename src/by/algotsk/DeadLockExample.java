package by.algotsk;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {

    static void main() throws InterruptedException {
        callSyncDeadLock();
    }

    static void callSyncDeadLock() throws InterruptedException {
       // SynchronizedDeadLock sd = new SynchronizedDeadLock();
        ReentrantLockDeadLock sd = new ReentrantLockDeadLock(); // этот лок не видно в jstack

        Runnable runA = sd::methodA;
        Runnable runB = sd::methodB;

        Thread threadA = new Thread(runA);
        Thread threadB = new Thread(runB);

        threadA.start();
        threadB.start();
/*
        Thread threadA = Thread.startVirtualThread(runA);  // ПРикол в том, что в виртуальных потоках не написано явно про дедлок т.к. jstack <pid> их не видит, только основные потоки. Чтобы увидеть и виртуальные нужен флаг -е  jcmd 3370779 Thread.print -e
        Thread threadB = Thread.startVirtualThread(runB);
        */

        threadA.join();
        threadB.join();

        System.out.println("Finished");
    }
}

class SynchronizedDeadLock {
    Object aLock = new Object();
    Object bLock = new Object();

    void methodA() {
        synchronized (aLock) {
            sleep(1000);  // важная пауза между захватами блокировки, чтобы оба захватили верхнюю блокировку, но не успели захватить нижнюю
            synchronized (bLock) {

                System.out.println("Thread one");
            }
        }
    }

    void methodB() {
        synchronized (bLock) {
            sleep(1000);
            synchronized (aLock) {
                System.out.println("Thread two");
            }
        }
    }

    public void sleep(int a)
    {
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class ReentrantLockDeadLock {
    ReentrantLock lockA = new ReentrantLock();
    ReentrantLock lockB = new ReentrantLock();

    void methodA() {
        lockA.lock();

        {
            sleep(100);
            lockB.lock();

            System.out.println("Thread one");
        }
    }

    void methodB() {
        lockB.lock();

        {
            sleep(100);
            lockA.lock();
            {
                System.out.println("Thread two");
            }
        }
    }

    public void sleep(int a) {
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
