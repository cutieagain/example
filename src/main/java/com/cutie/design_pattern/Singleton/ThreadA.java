package com.cutie.design_pattern.Singleton;

/**
 * Created by cutie on 2017/11/4.
 */
public class ThreadA extends Thread{
    @Override
    public void run(){
        System.out.println(Singleton.getInstance().hashCode());//根据实例对象哈希值是否相同来判断对象是否相同
    }

    public static void main(String[] args) {
        for (int i=0;i<1000;i++){
            ThreadA t1=new ThreadA();
            t1.start();
        }


    }
}
