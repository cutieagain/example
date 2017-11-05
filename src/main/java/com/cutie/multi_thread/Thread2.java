package com.cutie.multi_thread;

/**
 * Created by cutie on 2017/11/5.
 */
public class Thread2 extends Thread {
    /**
     * 调用start()方法和执行顺序无关
     * 调用run方法和执行顺序有关
     */

    @Override
    public void run() {
        showThread();
    }

    /**
     * 1.start（）方法来启动线程，真正实现了多线程运行，这时无需等待run方法体代码执行完毕而直接继续执行下面的代码：
     通过调用Thread类的start()方法来启动一个线程，
     这时此线程是处于就绪状态，
     并没有运行。
     然后通过此Thread类调用方法run()来完成其运行操作的，
     这里方法run()称为线程体，
     它包含了要执行的这个线程的内容，
     Run方法运行结束，
     此线程终止，
     而CPU再运行其它线程，

     2.run（）方法当作普通方法的方式调用，程序还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码：
     而如果直接用Run方法，
     这只是调用一个方法而已，
     程序中依然只有主线程--这一个线程，
     其程序执行路径还是只有一条，
     这样就没有达到写线程的目的。
     */

    /**
     * 演示线程的随机性
     * @param args
     */
    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        thread2.setName("my_thread");
        thread2.start();
        showThread();
    }

    public static void showThread(){
        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random()*1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run:" + Thread.currentThread().getName() + "id:" +Thread.currentThread().getId());
        }
    }

}
