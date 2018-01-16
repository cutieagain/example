package com.cutie.design_pattern.singleton;

/**
 * Created by Administrator on 2017/11/2.
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton(){

    }

    /**
     * 懒汉式单例模式
     * 普通单例，如果是null的时候自己生成
     * @return
     */
    public static Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    /**
     * 同步的懒汉式单例模式，如果多线程的情况下可以使用synchronized同步方法
     * @return
     */
    public static synchronized Singleton getInstanceSync(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public static void main(String[] args) {

           Runnable task =  new Runnable(){
                public void run() {
                    /*Singleton singleton = Singleton.getInstance();
                    System.out.println(singleton);*/
                    String threadName = Thread.currentThread().getName();
                    Singleton s1 = Singleton.getInstance();
                    System.out.println("线程 " + threadName + "\t => " + s1.hashCode());
                }
            };

            // 模拟多线程环境下使用 Singleton 类获得对象
            for(int i=0;i<1000;i++){
                new Thread(task,"" + i).start();
            }

    }

}
