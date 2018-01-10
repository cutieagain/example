package com.cutie.algorithm.sort;

/**
 * Created by cutie on 2018/1/10.
 */
public class TestSort2 {
    public static void main(String[] args) {
        AlgorithmUtils<Integer> algorithmUtils = new AlgorithmUtils();
        Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 54, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};

        algorithmUtils.print(array);
        test1(array);
        algorithmUtils.print(array);
    }
    /*直接插入*/
    /*每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置，直到全部插入排序完为止。*/
    private static void test1(Integer[] a){
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            for (int j = i-1; j >= 0 ; j--) {
                if (a[j] > temp){
                    a[j+1] = a[j];
                }else{
                    break;
                }
                a[j] = temp;
            }
        }
    }



}
