package com.cutie.design_pattern.head_first.strategy.quack;

/**
 * Created by Cutie on 2018/1/16.
 */
public class SQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("SQuack");
    }
}
