package com.cutie.design_pattern.explained_simply.abstract_factory.cutie;

/**
 * Created by Cutie on 2018/1/23.
 */
public abstract class AProduct1 implements IProduct{
    protected String name;
    protected void beforePrint(){
        System.out.println("AProduct1 beforePrint");
    }
}
