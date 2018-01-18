package com.cutie.java_grammar.thread.handle_exception;

import java.util.concurrent.*;

/**
 * Created by Cutie on 2018/1/18.
 */
public class HandleThreadExceptionTest {
    private SimpleTask task = new SimpleTask();
    private MyFactory factory = new MyFactory(task);

    public static void main(String[] args) {
        HandleThreadExceptionTest plan = new HandleThreadExceptionTest();
//        plan.start();

//        ExecutorService pool = Executors.newSingleThreadExecutor(plan.factory);
//        pool.execute(plan.task);
//        pool.shutdown();

        plan.start(plan.task);
    }


    static class SimpleTask implements Runnable{
        private int task = 10;

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"--"+"启动");
            while(task>0){
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if(System.currentTimeMillis()%3==0){
                    throw new RuntimeException("模拟异常");
                }
                System.out.println(threadName+"--"+"执行task"+task);
                task--;
            }
            System.out.println(threadName+"--"+"正常终止");
        }
    }

    public void start(){
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(e.getMessage());
            start();
        });
        thread.start();
    }

    class MyFactory implements ThreadFactory {

        private SimpleTask task;

        public MyFactory(SimpleTask task) {
            super();
            this.task = task;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler((t, e) -> {
                ExecutorService pool = Executors.newSingleThreadExecutor(new MyFactory(task));
                pool.execute(task);
                pool.shutdown();
            });
            return thread;
        }
    }

    public void start(SimpleTask task){
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> future = pool.scheduleAtFixedRate(task, 0, 1000, TimeUnit.MILLISECONDS);
        try {
            future.get();
        }catch (InterruptedException | ExecutionException e){
            System.out.println(e.getMessage());
            start(task);
        }finally {
            pool.shutdown();
        }

    }
}
