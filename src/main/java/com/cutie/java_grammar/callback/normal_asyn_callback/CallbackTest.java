package com.cutie.java_grammar.callback.normal_asyn_callback;

/**
 * Created by Cutie on 2018/1/22.
 */
public class CallbackTest {
    public static void main(String[] args) {
        Cutie cutie = new Cutie(new Cyy());
        cutie.askCyy("do you love me?");
    }
}
