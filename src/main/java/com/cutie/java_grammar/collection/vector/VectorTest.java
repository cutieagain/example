package com.cutie.java_grammar.collection.vector;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Cutie on 2018/1/16.
 */
public class VectorTest {


    public static void main(String[] args) {
        Vector<Integer> intVec = new Vector<>();

        class Oper extends Thread{
            @Override
            public void run() {
                Iterator iterator = intVec.iterator();
                while(iterator.hasNext()){
                    iterator.remove();
                    System.out.println("Oper remove:" + iterator.next());
                }
                /*for (int i = 0; i < 100; i++) {
                    intVec.remove(i);
                    System.out.println("Oper remove:" + i);
                   *//* try {
                        System.out.println("sleep(500);");
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*//*
                }*/
            }
        }

        /**
         * 不管哪种遍历，都会有问题
         */
        class Oper2 extends Thread{
            @Override
            public void run() {
                Iterator iterator = intVec.iterator();
                while(iterator.hasNext()){
                    System.out.println("Oper2:" + (Integer)iterator.next());
                }
                /*for (Integer intVal:intVec) {
                    System.out.println("Oper2:" + intVal);
                }*/
            }
        }

        for (int i = 0; i < 100; i++) {
            intVec.addElement(i);
        }
        Oper oper = new Oper();
        Oper2 oper2 = new Oper2();
        oper.start();
        oper2.start();
    }
}
