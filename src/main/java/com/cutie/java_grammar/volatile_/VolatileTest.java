package com.cutie.java_grammar.volatile_;

/**
 * Created by cutie on 2018/2/8.
 */
public class VolatileTest {
    public volatile static int inc = 0;

    public static void main(String[] args) {
        seeAble();
    }

    public static void seeAble(){
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++) inc++;
                    System.out.println(inc);
                }
            }.start();
        }
    }
}
