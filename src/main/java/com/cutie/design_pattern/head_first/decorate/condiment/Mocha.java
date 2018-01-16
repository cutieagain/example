package com.cutie.design_pattern.head_first.decorate.condiment;

import com.cutie.design_pattern.head_first.decorate.beverage.Beverage;

/**
 * Created by Cutie on 2018/1/16.
 */
public class Mocha extends Condiment {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }
}
