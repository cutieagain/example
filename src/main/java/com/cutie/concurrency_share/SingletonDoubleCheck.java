package com.cutie.concurrency_share;

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

     但是在 JVM 的即时编译器中存在指令重排序的优化。也就是说上面的第二步和第三步的顺序是不能保证的，
     最终的执行顺序可能是 1-2-3 也可能是 1-3-2。如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，
     这时 instance 已经是非 null 了（但却没有初始化），所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。

     所以这里的 volatile 主要是用来做禁止指令重排序的。
     */
    private static volatile SingletonDoubleCheck singletonDoubleCheck;

    public static SingletonDoubleCheck getInstance() {
        if(null == singletonDoubleCheck){
            synchronized (SingletonDoubleCheck.class){
                if(null == singletonDoubleCheck){
                    singletonDoubleCheck = new SingletonDoubleCheck();
                }
            }
        }
        return singletonDoubleCheck;
    }

    public static void main(String[] args) {
        Runnable runnable =  new Runnable(){
            @Override
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
