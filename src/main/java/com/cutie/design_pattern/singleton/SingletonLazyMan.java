package com.cutie.design_pattern.singleton;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/11/2.
 */
public class SingletonLazyMan {
    /**
     * 这段代码简单明了，而且使用了懒加载模式，但是却存在致命的问题。
     * 当有多个线程并行调用 getInstance() 的时候，就会创建多个实例。
     * 也就是说在多线程下不能正常工作。
     */

    private static SingletonLazyMan singletonLazyMan;

    private SingletonLazyMan(){

    }

    /**
     * 懒汉式单例模式
     * 普通单例，如果是null的时候自己生成
     * @return
     */
    public static SingletonLazyMan getInstance(){
        if(singletonLazyMan == null){
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singletonLazyMan = new SingletonLazyMan();
        }
        return singletonLazyMan;
    }

    public static void main(String[] args) {
           Runnable runnable =  new Runnable(){
                public void run() {
                    SingletonLazyMan singletonLazyMan = SingletonLazyMan.getInstance();
                    System.out.println(singletonLazyMan.hashCode());
                }
            };
            for (int i=0;i<100;i++){
                Thread thread = new Thread(runnable);
                thread.start();
            }
    }

}
