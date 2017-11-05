package com.cutie.design_pattern.Singleton;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * Created by Administrator on 2017/11/2.
 */
public class SingletonLazyManSync {
    /**
     * 虽然做到了线程安全，并且解决了多实例的问题，但是它并不高效。
     * 因为在任何时候只能有一个线程调用 getInstance() 方法。但是同
     * 步操作只需要在第一次调用时才被需要，即第一次创建单例实例对象
     * 时。这就引出了双重检验锁。
     */

    private static SingletonLazyManSync singletonLazyManSync;

    private SingletonLazyManSync(){

    }

    /**
     * 同步的懒汉式单例模式，如果多线程的情况下可以使用synchronized同步方法
     * 并发高的时候使用
     *
     * 这种实现方式比较粗糙，在使用的时候，每次都需要加锁，理论上说，创建完后，可以允许多线程读。
     *
     * @return
     */
    public static synchronized SingletonLazyManSync getInstance(){
        if(singletonLazyManSync == null){
            singletonLazyManSync = new SingletonLazyManSync();
        }
        return singletonLazyManSync;
    }

    public static void main(String[] args) {
        Runnable runnable =  new Runnable(){
            public void run() {
                SingletonLazyManSync singletonLazyManSync = SingletonLazyManSync.getInstance();
                System.out.println(singletonLazyManSync.hashCode());
            }
        };
        for (int i=0;i<100;i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
