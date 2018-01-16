package com.cutie.design_pattern.head_first.strategy;

import com.cutie.design_pattern.head_first.strategy.duck.Duck;
import com.cutie.design_pattern.head_first.strategy.duck.ModelDuck;
import com.cutie.design_pattern.head_first.strategy.fly.FlyRocketPower;

/**
 * Created by Cutie on 2018/1/16.
 */
public class DuckTest {
    public static void main(String[] args) {
        /*Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();
        mallardDuck.setQuackBehavior(new Quack());
        mallardDuck.setFlyBehavior(new FlyNoWay());
        mallardDuck.performFly();
        mallardDuck.performQuack();*/

        Duck modelDuck = new ModelDuck();
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyRocketPower());
        modelDuck.performFly();
    }
}
