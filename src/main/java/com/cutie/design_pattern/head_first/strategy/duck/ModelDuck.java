package com.cutie.design_pattern.head_first.strategy.duck;

import com.cutie.design_pattern.head_first.strategy.fly.FlyNoWay;
import com.cutie.design_pattern.head_first.strategy.quack.Quack;

/**
 * Created by Cutie on 2018/1/16.
 */
public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display(){
        System.out.println("i am a ModelDuck");
    }
}
