package com.cutie.design_pattern.head_first.decorate.beverage;

/**
 * Created by Cutie on 2018/1/16.
 */
public abstract class Beverage {
    protected String description = "unknown beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}
