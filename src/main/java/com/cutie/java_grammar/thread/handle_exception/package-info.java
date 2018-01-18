/**
 * Created by Cutie on 2018/1/18.
 */
package com.cutie.java_grammar.thread.handle_exception;

/*
* Java线程池异常处理最佳实践
*   http://blog.onlycatch.com/post/Java%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5

*   1. 自定义线程池，继承ThreadPoolExecutor并复写其afterExecute(Runnable r, Throwable t)方法。
    2. 实现Thread.UncaughtExceptionHandler接口，实现void uncaughtException(Thread t, Throwable e);方法，并将该handler传递给线程池的ThreadFactory
    3. 采用Future模式，将返回结果以及异常放到Future中，在Future中处理
    4. 继承ThreadGroup，覆盖其uncaughtException方法。（与第二种方式类似，因为ThreadGroup类本身就实现了Thread.UncaughtExceptionHandler接口)
*
    java线程池会捕获任务抛出的异常和错误，但不做任何处理
    好的程序设计应该考虑到对于类异常的处理
    处理线程池中的异常有两种思路：
        1）提交到线程池中的任务自己捕获异常并处理，不抛给线程池
        2）由线程池统一处理
    对于execute方法提交的线程，有两种处理方式
        1）自定义线程池并实现afterExecute方法
        2）给线程池中的每个线程指定一个UncaughtExceptionHandler,由handler来统一处理异常。
    对于submit方法提交的任务，异常处理是通过返回的Future对象进行的。
*
*
*
*
*
*
*
*
*
线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
1）newFixedThreadPool和newSingleThreadExecutor:
  主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
2）newCachedThreadPool和newScheduledThreadPool:
  主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。

Positive example 1：
    //org.apache.commons.lang3.concurrent.BasicThreadFactory
    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
        new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());



Positive example 2：
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("demo-pool-%d").build();

    //Common Thread Pool
    ExecutorService pool = new ThreadPoolExecutor(5, 200,
         0L, TimeUnit.MILLISECONDS,
         new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    pool.execute(()-> System.out.println(Thread.currentThread().getName()));
    pool.shutdown();//gracefully shutdown



Positive example 3：
    <bean id="userThreadPool"
        class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
        <property name="corePoolSize" value="10" />
        <property name="maxPoolSize" value="100" />
        <property name="queueCapacity" value="2000" />

    <property name="threadFactory" value= threadFactory />
        <property name="rejectedExecutionHandler">
            <ref local="rejectedExecutionHandler" />
        </property>
    </bean>
    //in code
    userThreadPool.execute(thread);



*
* */