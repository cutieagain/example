package com.cutie.multi_thread;

/**
 * Created by cutie on 2017/11/5.
 */
public class Thread3 extends Thread{
    public Thread3() {

    }

    public Thread3(String name) {
        this.setName(name);
    }

    volatile private int count = 0;

    @Override
    public void run() {
        /*while(count<5){
            count++;
            System.out.println("name:" + this.getName() + " ; count:" + count);
        }*/
        count++;
        System.out.println("name:" + this.currentThread().getName() + " ; count:" + count);
    }

    public static void main(String[] args) {
        Thread3 thread3 = new Thread3();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(thread3);
            thread.start();
        }
    }

}
