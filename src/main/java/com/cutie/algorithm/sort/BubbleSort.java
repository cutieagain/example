package com.cutie.algorithm.sort;

/**
 * Created by cutie on 2017/12/10.
 */
public class BubbleSort {
    public static void main(String[] args) {
        AlgorithmUtils<Integer> algorithmUtils = new AlgorithmUtils();
        Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 52, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};
//        algorithmUtils.print(array);
        array = bubbleSortMethod2(array);
        algorithmUtils.print(array);
    }

    static Integer[] bubbleSortMethod(Integer[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[i] < array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    static Integer[] bubbleSortMethod2(Integer[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }


}
