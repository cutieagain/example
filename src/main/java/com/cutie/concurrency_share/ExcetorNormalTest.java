package com.cutie.concurrency_share;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
*
* newCachedThreadPool()
-缓存型池子，先查看池中有没有以前建立的线程，如果有，就重新使用.如果没有，就建一个新的线程加入池中
-缓存型池子通常用于执行一些生存期很短的异步型任务
 因此在一些面向连接的daemon型SERVER中用得不多。但对于生存期短的异步任务，它是Executor的首选。
-能reuse的线程，必须是timeout IDLE内的池中线程，缺省timeout是60s,超过这个IDLE时长，线程实例将被终止及移出池。
  注意，放入CachedThreadPool的线程不必担心其结束，超过TIMEOUT不活动，其会自动被终止。

newFixedThreadPool(int)
-newFixedThreadPool与cacheThreadPool差不多，也是能reuse就用，但不能随时建新的线程
-其独特之处:任意时间点，最多只能有固定数目的活动线程存在，此时如果有新的线程要建立，只能放在另外的队列中等待，直到当前的线程中某个线程终止直接被移出池子
-和cacheThreadPool不同，FixedThreadPool没有IDLE机制（可能也有，但既然文档没提，肯定非常长，类似依赖上层的TCP或UDP IDLE机制之类的），所以FixedThreadPool多数针对一些很稳定很固定的正规并发线程，多用于服务器
-从方法的源代码看，cache池和fixed 池调用的是同一个底层 池，只不过参数不同:
fixed池线程数固定，并且是0秒IDLE（无IDLE）
cache池线程数支持0-Integer.MAX_VALUE(显然完全没考虑主机的资源承受能力），60秒IDLE

newScheduledThreadPool(int)
-调度型线程池
-这个池子里的线程可以按schedule依次delay执行，或周期执行

SingleThreadExecutor()
-单例线程，任意时间池中只能有一个线程
-用的是和cache池和fixed池相同的底层池，但线程数目是1-1,0秒IDLE（无IDLE）

ForkJoinPool

* */
/**
 * Created by cutie on 2018/2/9.
 * 默认的线程池的使用
 */
public class ExcetorNormalTest {
    public static void main(String[] args){
        /*ExecutorService executorService = Executors.newCachedThreadPool();
//      ExecutorService executorService = Executors.newFixedThreadPool(5);
//      ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++){
            executorService.execute(new RunnableTest("Thread-1"));
        }
        executorService.shutdown();*/
        scheduleExe();
    }

    /**
     * ScheduledExecutorService 中两种最常用的调度方法 ScheduleAtFixedRate 和 ScheduleWithFixedDelay。ScheduleAtFixedRate
     * 每次执行时间为上一次任务开始起向后推一个时间间隔，即每次执行时间为 :initialDelay, initialDelay+period, initialDelay+2*period, …；
     * ScheduleWithFixedDelay 每次执行时间为上一次任务结束起向后推一个时间间隔，即每次执行时间为：initialDelay, initialDelay+executeTime+delay,
     * initialDelay+2*executeTime+2*delay。由此可见，ScheduleAtFixedRate 是基于固定时间间隔进行任务调度，ScheduleWithFixedDelay
     * 取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度。
     */
    private static void scheduleExe(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay1 = 1;
        long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
       /*创建一个给定初始延迟的间隔性的任务，之后的每次任务执行时间为 初始延迟 + N * delay(间隔)  。
        这里的N为首次任务执行之后的第N个任务，N从1开始，意识就是 首次执行任务的时间为12:00 那么下次任务的执
        行时间是固定的 是12:01 下下次为12:02。
        与scheduleWithFixedDelay 最大的区别就是 ，scheduleAtFixedRate不受任务执行时间的影响。*/
        service.scheduleAtFixedRate(
                new RunnableTest("Thread-1"), initialDelay1,
                period1, TimeUnit.SECONDS);

        long initialDelay2 = 3;
        long delay2 = 5;
        // 从现在开始3秒钟之后，每隔5秒钟执行一次job2
         /*创建一个给定初始延迟的间隔性的任务，之后的下次执行时间是上一次任务从执行到结束所需要的时间+给定的间隔时间。
        举个例子：比如我给定任务的初始延迟(long initialdelay)是12:00， 间隔为1分钟 。 那么这个任务会在12:00 首次创建并执行，
        如果该任务从执行到结束所需要消耗的时间为1分钟，那么下次任务执行的时间理应从12：01 再加上设定的间隔1分钟，
        那么下次任务执行时间是12:02 。这里的间隔时间（delay） 是从上次任务执行结束开始算起的。*/
        service.scheduleWithFixedDelay(
                new RunnableTest("Thread-2"), initialDelay2,
                delay2, TimeUnit.SECONDS);
    }
}


