package com.cutie.algorithm.sort;

/**
 * Created by cutie on 2017/12/10.
 */
public class InsertSort {
    public static void main(String[] args) {
        AlgorithmUtils<Integer> algorithmUtils = new AlgorithmUtils();
        Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 52, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};
        algorithmUtils.print(array);
        insertSortMethod2(array);
        algorithmUtils.print(array);
    }

    static Integer[] insertSortMethod(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            Integer currentNum = array[i];
            int j = i - 1;
            while (j > 0 && array[j] > currentNum) {
                array[j + 1] = array[j];
                j--;//0
            }
            array[j + 1] = currentNum;
        }
        return array;
    }

    static Integer[] insertSortMethod2(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (array[j - 1] >= array[j]) {
                    break;
                }
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
        return array;
    }
}
