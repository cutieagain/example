package com.cutie.design_pattern.head_first.strategy.fly;

/**
 * Created by Cutie on 2018/1/16.
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("FlyNoWay");
    }
}
