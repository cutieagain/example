package com.cutie.java_grammar.callback.normal_asyn_callback;

/**
 * Created by Cutie on 2018/1/22.
 */
public class Cyy {
    public void solve(Callback callback, String question){
        try {
            //想了很久
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("question is " + question);
        callback.solve( "Cyy : i have no idea");
    }
}
