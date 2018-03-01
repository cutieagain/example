package com.cutie.concurrency_share;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cutie on 2018/2/9.
 * 线程工厂生产自定义线程，异常处理
 */
public class ThreadPoolExecutorTest {
    /*
    *
    *   ThreadPoolExecutor.CallerRunsPolicy
    *   既不抛弃任务也不抛出异常，直接运行任务的run方法，换言之将任务回退给调用者来直接运行。
    *   使用该策略时线程池饱和后将由调用线程池的主线程自己来执行任务，因此在执行任务的这段时
    *   间里主线程无法再提交新任务，从而使线程池中工作线程有时间将正在处理的任务处理完成。
    *
        ThreadPoolExecutor.DiscardOldestPolicy
        先将阻塞队列中的头元素出队抛弃，再尝试提交任务。如果此时阻塞队列使用PriorityBlockingQueue
        优先级队列，将会导致优先级最高的任务被抛弃，因此不建议将该种策略配合优先级队列使用。

        ThreadPoolExecutor.DiscardPolicy
        不做任何处理直接抛弃任务

        new ThreadPoolExecutor.AbortPolicy()
        该策略是默认饱和策略。
        使用该策略时在饱和时会抛出RejectedExecutionException（继承自RuntimeException），调用者可捕获该异常自行处理。
    * */
    public static void main(String[] args) {
        //创建线程池，池中保存的线程数为3，允许的最大线程数为5
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new NamedThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new RunnableTest("Thread-1"));
        }

        threadPoolExecutor.shutdown();
    }
}

class NamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger threadNumber = new AtomicInteger(1);
    private final AtomicInteger mThreadNum = new AtomicInteger(1);
    private final String prefix;
    private final boolean daemoThread;
    private final ThreadGroup threadGroup;

    public NamedThreadFactory() {
        this("rpcserver-threadpool-" + threadNumber.getAndIncrement(), false);
    }

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemo) {
        this.prefix = prefix + "-thread-";
        daemoThread = daemo;
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String name = prefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(threadGroup, runnable, name, 0);
        ret.setDaemon(daemoThread);
        ret.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            /*当单线程的程序发生一个未捕获的异常时我们可以采用try….catch进行异常的捕获，但是在多线程环境中，
            线程抛出的异常是不能用try….catch捕获的，这样就有可能导致一些问题的出现，比如异常的时候无法回收
            一些系统资源，或者没有关闭当前的连接等等。
            */
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //do some handle
                System.out.println("Exception: "+e.getMessage());
            }
        });
        System.out.println(String.format("Created thread " + ret.getId() + " with name " + ret.getName()));
        return ret;
    }
}