package com.cutie.design_pattern.head_first.decorate.beverage;

/**
 * Created by Cutie on 2018/1/16.
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        this.description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
