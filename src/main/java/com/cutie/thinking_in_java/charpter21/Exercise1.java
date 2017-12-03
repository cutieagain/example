package com.cutie.thinking_in_java.charpter21;

/**
 * Created by cutie on 2017/12/3.
 * 实现一个Runnable，在run()内部打印一个消息，然后调用yield()，重复3次，然后从run()中返回，
 * 在狗仔器中放置一条启动消息，并放置一条在任务终止时候的关闭消息
 * 使用线程创建大量的这种任务并驱动它们
 */
public class Exercise1 implements Runnable{
    private static int init = 0;
    private final int id = init++;

    public Exercise1() {
        System.out.println("exercise1 start id = " + id);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("id = " + id + " & " + ",i = " + i);
            Thread.yield();
        }
        System.out.println("exercise1 run over id = " + id);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Exercise1()).start();
        }
        System.out.println("exercise1 start run over");
    }
}
