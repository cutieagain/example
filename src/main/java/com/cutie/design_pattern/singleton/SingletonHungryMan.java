package com.cutie.design_pattern.singleton;

/**
 * Created by Administrator on 2017/11/2.
 */
public class SingletonHungryMan {
    /**
     * 还有一种，直接在初始化的时候就new一个，创建和运行时候负担不严重的时候使用
     * @return
     */
    SingletonHungryMan(){

    }

    /**
     * 懒汉式单例模式
     */
    private static SingletonHungryMan singletonHungryMan = new SingletonHungryMan();

    public static SingletonHungryMan getInstance(){
        return singletonHungryMan;
    }

    public static void main(String[] args) {
        Runnable runnable =  new Runnable(){
            public void run() {
                SingletonHungryMan singletonHungryMan = SingletonHungryMan.getInstance();
                System.out.println(singletonHungryMan.hashCode());
            }
        };
        for (int i=0;i<100;i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
