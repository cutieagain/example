package com.cutie.design_pattern.head_first.decorate;

import com.cutie.design_pattern.head_first.decorate.beverage.Beverage;
import com.cutie.design_pattern.head_first.decorate.beverage.Expresso;
import com.cutie.design_pattern.head_first.decorate.condiment.Mocha;
import com.cutie.design_pattern.head_first.decorate.condiment.Whip;

/**
 * Created by Cutie on 2018/1/16.
 */
public class DecorateTest {
    public static void main(String[] args) {
        Beverage beverage = new Expresso();
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);
        System.out.println("name:" + beverage.getDescription() + ",cost:" + beverage.cost());
    }
}
