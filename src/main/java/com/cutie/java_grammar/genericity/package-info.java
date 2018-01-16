/**
 * Created by Cutie on 2018/1/16.
 */
package com.cutie.java_grammar.genericity;

/*
    http://www.hollischuang.com/archives/1182

*   泛型
*   E – Element (在集合中使用，因为集合中存放的是元素)
    T – Type（Java 类）
    K – Key（键）
    V – Value（值）
    N – Number（数值类型）
    ？ – 表示不确定的java类型（无限制通配符类型）
    S、U、V – 2nd、3rd、4th types
    Object – 是所有类的根类，任何类的对象都可以设置给该Object引用变量，使用的时候可能需要类型强制转换，
    但是用使用了泛型T、E等这些标识符后，在实际用之前类型就已经确定了，不需要再进行类型强制转换。


*
* */