package com.cutie.design_pattern.head_first.strategy.duck;

import com.cutie.design_pattern.head_first.strategy.fly.FlyBehavior;
import com.cutie.design_pattern.head_first.strategy.quack.QuackBehavior;

/**
 * Created by Cutie on 2018/1/16.
 */
public abstract class Duck {
    /**
     * 策略模式
     * 定义了算法族，分别封装起来，让他们之间可以相互替换，此模式让算法独立于使用算法的用户
     *
     *
     * 改变的部分利用组合放入到类中，
     * 让子类继承，能动态设置所需要的行为
     */
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("Duck swim");
    }

    public void display(){
        System.out.println("Duck display");
    }
}
