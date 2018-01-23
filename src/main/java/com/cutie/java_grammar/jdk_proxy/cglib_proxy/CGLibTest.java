package com.cutie.java_grammar.jdk_proxy.cglib_proxy;

/**
 * Created by Cutie on 2018/1/22.
 */
public class CGLibTest {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        SayHello proxyImp = (SayHello)proxy.getProxy(SayHello.class);
        proxyImp.say();
    }
}
