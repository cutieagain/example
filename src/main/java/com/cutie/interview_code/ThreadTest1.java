package com.cutie.interview_code;

/**
 * Created by cutie on 2017/12/24.
 */
public class ThreadTest1 implements Runnable{
    //尝试编写一个程序，让它创建100个线程，这些线程什么事情都不做，只是在等待
    @Override
    public void run() {
        System.out.println("start thread :" + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        ThreadTest1 threadTest1 = new ThreadTest1();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(threadTest1);
            thread.start();

        }
    }
}
