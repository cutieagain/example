package com.cutie.design_pattern.head_first.decorate.beverage;

/**
 * Created by Cutie on 2018/1/16.
 */
public class Expresso extends Beverage {
    public Expresso() {
        this.description = "Expresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
