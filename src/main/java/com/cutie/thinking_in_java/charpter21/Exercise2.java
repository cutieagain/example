package com.cutie.thinking_in_java.charpter21;

/**
 * Created by cutie on 2017/12/3.
 * 遵循generic/Fibonacci.java的形式，创建一个任务，它可以产生由n个由斐波那契数列组成的序列
 * 其中n是通过任务的构造器提供的。使用线程创建大量的这种任务并驱动它们
 */
public class Exercise2 implements Runnable{
    int n;
    public Exercise2(int n){
        this.n = n;
    }

    public int f(int n){
        return n > 2 ? f(n-1)+f(n-2) : 1;
    }

    @Override
    public void run() {
        for (int i = 1; i < n; i++) {
            System.out.print(f(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Thread(new Exercise2(7)).start();
    }
}
