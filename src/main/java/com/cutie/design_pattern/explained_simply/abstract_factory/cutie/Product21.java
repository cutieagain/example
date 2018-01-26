package com.cutie.design_pattern.explained_simply.abstract_factory.cutie;

/**
 * Created by Cutie on 2018/1/23.
 */
public class Product21 extends AProduct2 {
    public Product21(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public void print() {
        this.beforePrint();
        System.out.println("Product21 print " + name + " " + type);
    }
}
