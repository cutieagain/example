package com.cutie.design_pattern.explained_simply.abstract_factory.cutie;

/**
 * Created by Cutie on 2018/1/23.
 */
public class Product22 extends AProduct2 {
    @Override
    public void print() {
        this.beforePrint();
        System.out.println("Product22 print");
    }
}
