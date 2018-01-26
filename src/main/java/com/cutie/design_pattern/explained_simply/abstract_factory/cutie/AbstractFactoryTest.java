package com.cutie.design_pattern.explained_simply.abstract_factory.cutie;

/**
 * Created by Cutie on 2018/1/23.
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        IFactory factory1 = new Factory1();
        IProduct product1 = factory1.createProdoct();
        product1.print();
        IFactory factory2 = new Factory2();
        IProduct product2 = factory2.createProdoct();
        product2.print();
    }
}
