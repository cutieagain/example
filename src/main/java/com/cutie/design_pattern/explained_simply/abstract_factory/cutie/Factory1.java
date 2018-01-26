package com.cutie.design_pattern.explained_simply.abstract_factory.cutie;

/**
 * Created by Cutie on 2018/1/23.
 */
public class Factory1 implements IFactory {
    @Override
    public IProduct createProdoct() {
        return new Product11("stupid");
    }
}
