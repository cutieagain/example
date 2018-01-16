package com.cutie.design_pattern.head_first.decorate.condiment;

import com.cutie.design_pattern.head_first.decorate.beverage.Beverage;

/**
 * Created by Cutie on 2018/1/16.
 */
public class Whip extends Condiment {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 0.11 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Whip";
    }
}
