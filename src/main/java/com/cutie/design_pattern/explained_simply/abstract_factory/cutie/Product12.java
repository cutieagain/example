package com.cutie.design_pattern.explained_simply.abstract_factory.cutie;

/**
 * Created by Cutie on 2018/1/23.
 */
public class Product12 extends AProduct1 {
    @Override
    public void print() {
        this.beforePrint();
        System.out.println("Product12 print");
    }
}
