package com.cutie.design_pattern.explained_simply.abstract_factory.cutie;

/**
 * Created by Cutie on 2018/1/23.
 */
public class Product11 extends AProduct1 {
    public Product11(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        this.beforePrint();
        System.out.println("Product11 print " + name);
    }
}
