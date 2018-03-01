package com.cutie.concurrency_share;

/**
 * Created by cutie on 2017/11/4.
 */
public class SingletonStaticNested {
    public SingletonStaticNested() {
        System.out.println("SingletonStaticNested constructor");
    }

    /**
     * 这种写法仍然使用JVM本身机制保证了线程安全问题；由于 SingletonHolder 是私有的，
     * 除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；同时读取实例的时候不
     * 会进行同步，没有性能缺陷；也不依赖 JDK 版本。
     *
     * 这种方法就是基于上述JVM在类的初始化阶段给出的线程安全性保证，将 SingletonStaticNested 的实例化操作放置到一个静态内部类中，
     * 在第一次调用 getInstance() 方法时，JVM才会去加载 SingletonHolder 类，同时初始化 SingletonStaticNested 实例，
     * 因此，即使我们不采取任何同步策略，getInstance() 方法也是线程安全的。
     */

    private static class SingletonHolder{
        private static final SingletonStaticNested singletonStaticNested = new SingletonStaticNested();

        static{
            System.out.println("SingletonHolder static");
        }
        public SingletonHolder() {
            System.out.println("SingletonHolder constructor");
        }
    }

    public static SingletonStaticNested getInstance(){
        return SingletonHolder.singletonStaticNested;
    }

    public static void main(String[] args) {
        Runnable runnable =  new Runnable(){
            public void run() {
                SingletonStaticNested singletonStaticNested = SingletonStaticNested.getInstance();
                System.out.println(singletonStaticNested.hashCode());
            }
        };
        for (int i=0;i<10;i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
