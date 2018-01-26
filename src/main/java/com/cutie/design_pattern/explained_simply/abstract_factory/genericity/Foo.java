package com.cutie.design_pattern.explained_simply.abstract_factory.genericity;

/**
 * Created by Cutie on 2018/1/23.
 */
public class Foo<T>{
    private T t;
    public <F extends IFactory<T>> Foo(F factory){
        // t = new T();
       t = factory.create();
    }

    public void print(){
        System.out.println(t.toString());
    }
}


