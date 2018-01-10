package com.cutie.algorithm.sort;

import java.util.Arrays;

/**
 * Created by cutie on 2017/12/10.
 */
public class QuickSort {
    public static void main(String[] args) {
        AlgorithmUtils<Integer> algorithmUtils = new AlgorithmUtils();
//        Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 54, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};
        Integer[] array = new Integer[]{3,5,4,3};
        algorithmUtils.print(array);
        quickSortWrong(array);
//        quick(array);
        algorithmUtils.print(array);
    }

    /*static Integer[] quickSortMethod(Integer[] array) {

        return array;
    }*/

    private static void quick(Integer[] a) {
        if (a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }
    }

    private static void quickSort(Integer[] a, int low, int high) {
        //如果不加这个判断递归会无法退出导致堆栈溢出异常
        if (low < high) {
            //基准元素的位置
            int middle = getMiddle(a, low, high);
            quickSort(a, 0, middle - 1);
            quickSort(a, middle + 1, high);
        }
    }

    private static int getMiddle(Integer[] a, int low, int high) {
        //基准元素
        int temp = a[low];
        while (low < high) {
            //找到比基准元素小的元素位置
            while (low < high && a[high] >= temp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] < temp) {
                low++;
            }
            a[high] = a[low];
        }
        //找到基准元素位置，赋值
        a[low] = temp;
        System.out.println(Arrays.toString(a));
        return low;
    }


    /*写错了，但是看不出来是为什么。。。*/
    private static void quickSortWrong(Integer[] a){
        if (a.length > 0){
            sortWrong(a, 0, a.length-1);
        }
    }

    private static void sortWrong(Integer[] a, int low , int high){
        //wrong here  “while (low < high) --> if (low < high)”
        if (low < high){
            int middle = getMidWrong(a, low , high);
            sortWrong(a, 0 , middle-1);
            sortWrong(a, middle+1 , high);
        }

    }

    //    Integer[] array = new Integer[]{10,9,8,3,7,6,3,5,4,3,2,1};
    private static int getMidWrong(Integer[] a, int low, int high) {
        int temp = a[low];
        while (low < high){
            while (low < high && a[high] >= temp){
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp){
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        System.out.println(Arrays.toString(a));
        return low;
    }

}
