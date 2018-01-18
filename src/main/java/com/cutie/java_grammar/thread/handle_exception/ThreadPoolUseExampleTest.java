package com.cutie.java_grammar.thread.handle_exception;

import java.util.concurrent.*;

/**
 * Created by Cutie on 2018/1/18.
 */
public class ThreadPoolUseExampleTest {
    public static void main(String[] args) {
        //org.apache.commons.lang3.concurrent.BasicThreadFactory
//        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

        //下面这个factory是干嘛用的？？？？
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("demo-pool-%d").build();

        ThreadFactory namedThreadFactory = new HandleThreadExceptionTest().new MyFactory(new HandleThreadExceptionTest.SimpleTask());

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

//        pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        pool.execute(new HandleThreadExceptionTest.SimpleTask());
        pool.shutdown();//gracefully shutdown

    }

}
