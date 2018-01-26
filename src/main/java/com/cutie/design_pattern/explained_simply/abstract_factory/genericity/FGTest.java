package com.cutie.design_pattern.explained_simply.abstract_factory.genericity;

/**
 * Created by Cutie on 2018/1/23.
 */
public class FGTest {
    public static void main(String[] args) {
        new Foo(new IFactory<Integer>(){
            @Override
            public Integer create(){
                return new Integer(0);
            }
        }).print();

        new Foo(new IFactory<String>(){
            @Override
            public String create(){
                return "Hello";
            }
        }).print();
    }
}
