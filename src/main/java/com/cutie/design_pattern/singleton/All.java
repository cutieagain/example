package com.cutie.design_pattern.singleton;

/**
 * Created by cutie on 2017/11/18.
 */
public class All {
    //懒汉模式
    /*
    private static final All all = new All();
    public static All getInstanceLH(){
        return all;
    }
    */

    //饿汉模式 不高效
    /*private static All all;
    public static synchronized All getInstanceEH(){
        if(all == null){
            return all;
        }
        return all;
    }*/

    //双重检查
    /*
    private static volatile All all;
    public static All getInstanceDoubleCheck(){
        if(all == null){
            synchronized (All.class){
                if(all == null){
                    all = new All();
                }
            }
        }
        return all;
    }
    */

    //静态内部类
    private static class AllInnerStaticClass{
        public static All all = new All();
    }

    public static All getStaticInnerInstance(){
        return AllInnerStaticClass.all;
    }

    //枚举？不懂

}
