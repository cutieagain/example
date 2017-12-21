package com.cutie.algorithm.sort;

/**
 * Created by cutie on 2017/12/10.
 */
public class AlgorithmUtils<T> {
    Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 52, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};

    void print(T[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
