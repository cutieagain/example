package com.cutie.java_grammar.callback.normal_asyn_callback;

/**
 * Created by Cutie on 2018/1/22.
 */
public class Cutie implements Callback {
    private Cyy cyy;

    public Cutie(Cyy cyy) {
        this.cyy = cyy;
    }

    public void askCyy(String question){
        //异步
        new Thread(new Runnable() {
            @Override
            public void run() {
                cyy.solve(Cutie.this, question);
            }
        }).start();
        play();
    }

    private void play() {
        System.out.println("i am going to play dota...");
    }

    @Override
    public void solve(String answer) {
        System.out.println("callback answer:" + answer);
    }

    /*
    避免Random实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一seed 导致的性能下降。说明：Random实例包括java.util.Random 的实例或者 Math.random()的方式。 说明：使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题。如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
    singleThreadPool.shutdown();
    */

}
