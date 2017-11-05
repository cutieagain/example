package com.cutie.design_pattern.Singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/11/2.
 */
public class SingletonDoubleCheck {
    /**
     * 双重检验锁模式（double checked locking pattern），是一种使用同步
     * 块加锁的方法。程序员称其为双重检查锁，因为会有两次检查 instance == null，
     * 一次是在同步块外，一次是在同步块内。为什么在同步块内还要再检验一次？
     * 因为可能会有多个线程一起进入同步块外的 if，如果在同步块内不进行二次
     * 检验的话就会生成多个实例了。
     */

    /**
     * 这里为什么用 volatile ，参考：点这里，主要还是因为 singleton = new Singleton(); 并不是原子性的，它会分为3步：
     1、给 instance 分配内存
     2、调用 Singleton 的构造函数来初始化成员变量
     3、将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）

     所以这里的 volatile 主要是用来做禁止指令重排序的。
     */
    private static volatile SingletonDoubleCheck singletonDoubleCheck;
    private static Lock lock = new ReentrantLock();

    public static SingletonDoubleCheck getInstance() {
        lock.lock();
        if(null == singletonDoubleCheck){
            synchronized (SingletonDoubleCheck.class){
                try{
                    if(null == singletonDoubleCheck){
                        singletonDoubleCheck = new SingletonDoubleCheck();
                    }
                }finally {
                    lock.unlock();
                }

            }
        }
        return singletonDoubleCheck;
    }

    public static void main(String[] args) {
        Runnable runnable =  new Runnable(){
            public void run() {
                SingletonDoubleCheck singletonDoubleCheck = SingletonDoubleCheck.getInstance();
                System.out.println(singletonDoubleCheck.hashCode());
            }
        };
        for (int i=0;i<100;i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

}
