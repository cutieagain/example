package com.cutie.share.concurrency_share;

/**
 * Created by cutie on 2018/2/9.
 * 线程测试
 */
public class ThreadTest extends Thread {
    private Thread t;
    private String threadName;

    ThreadTest(String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    @Override
    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    public static void main(String args[]) {
        ThreadTest T1 = new ThreadTest( "Thread-1");
        T1.start();

        ThreadTest T2 = new ThreadTest( "Thread-2");
        T2.start();
    }
}