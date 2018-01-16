package com.cutie.design_pattern.head_first.strategy.duck;

import com.cutie.design_pattern.head_first.strategy.fly.FlyWithWings;
import com.cutie.design_pattern.head_first.strategy.quack.SQuack;

/**
 * Created by Cutie on 2018/1/16.
 */
public class MallardDuck extends Duck {
    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new SQuack();
    }
}
