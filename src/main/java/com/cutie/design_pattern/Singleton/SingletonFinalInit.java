package com.cutie.design_pattern.Singleton;

/**
 * Created by cutie on 2017/11/4.
 */
public class SingletonFinalInit {
    /**
     * 这种方法非常简单，因为单例的实例被声明成 static 和 final 变量了，
     * 在第一次加载类到内存中时就会初始化，所以创建实例本身是线程安全的。
     *
     * 这种写法如果完美的话，就没必要在啰嗦那么多双检锁的问题了。缺点是
     * 它不是一种懒加载模式（lazy initialization），单例会在加载类后一开
     * 始就被初始化，即使客户端没有调用 getInstance()方法。饿汉式的创建
     * 方式在一些场景中将无法使用：譬如 Singleton 实例的创建是依赖参数或者
     * 配置文件的，在 getInstance() 之前必须调用某个方法设置参数给它，那样
     * 这种单例写法就无法使用了。
     */
    private static final SingletonFinalInit singletonFinalInit = new SingletonFinalInit();

    public SingletonFinalInit() {
    }

    public static SingletonFinalInit getInstance(){
        return singletonFinalInit;
    }

    public static void main(String[] args) {
        Runnable runnable =  new Runnable(){
            public void run() {
                SingletonFinalInit singletonFinalInit = SingletonFinalInit.getInstance();
                System.out.println(singletonFinalInit.hashCode());
            }
        };
        for (int i=0;i<100;i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

}
