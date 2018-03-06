package com.cutie.share.concurrency_share;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by cutie on 2018/2/9.
 * 返回结果的callable
 */
public class CallableTest {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        //创建10个任务并执行
        for (int i = 0; i < 10; i++){
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult("Thread-"+i));
            //将任务执行结果存储到List中
            resultList.add(future);
        }

        //遍历任务的结果
        for (Future<String> fs : resultList){
            try{
                //Future返回如果没有完成，则一直循环等待，直到Future返回完成
                while(!fs.isDone());
                //打印各个线程（任务）执行的结果
                System.out.println(fs.get());
            }catch(InterruptedException e){
                e.printStackTrace();
            }catch(ExecutionException e){
                e.printStackTrace();
            }finally{
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务
                executorService.shutdown();
            }
        }
    }
}

class TaskWithResult implements Callable<String> {
    private String threadName;

    public TaskWithResult(String threadName){
        this.threadName = threadName;
        System.out.println("Creating " +  threadName );
    }

    @Override
    public String call() throws Exception {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
        //该返回结果将被Future的get方法得到
        return "call()方法被自动调用，任务返回的结果是：" + threadName + " " + Thread.currentThread().getName();
    }
}