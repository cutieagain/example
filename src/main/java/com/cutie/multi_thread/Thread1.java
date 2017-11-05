package com.cutie.multi_thread;

/**
 * Created by cutie on 2017/11/5.
 */
public class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread1");
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        System.out.println("run over!");
    }
}
