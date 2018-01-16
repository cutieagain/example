package com.cutie.design_pattern.head_first.decorate.condiment;

import com.cutie.design_pattern.head_first.decorate.beverage.Beverage;

/**
 * 调料
 * Created by Cutie on 2018/1/16.
 */
public abstract class Condiment extends Beverage {
    public Condiment() {
        this.description = "Condiment";
    }

    /**
     * 所有调料的装饰者都需要重写描述
     * @return
     */
    @Override
    public abstract String getDescription();
}
