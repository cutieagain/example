/**
 * Created by Cutie on 2018/1/22.
 */
package com.cutie.java_grammar.jdk_proxy.cglib_proxy;

/*
* GLib创建的动态代理对象性能比JDK创建的动态代理对象的性能高不少，但是CGLib在创建代理对象时所花费的时间却比JDK多得多，
* 所以对于单例的对象，因为无需频繁创建对象，用CGLib合适，反之，使用JDK方式要更为合适一些。同时，由于CGLib由于是采用
* 动态创建子类的方法，对于final方法，无法进行代理
*
*
*
*
* */