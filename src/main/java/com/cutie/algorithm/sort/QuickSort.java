package com.cutie.algorithm.sort;

/**
 * Created by cutie on 2017/12/10.
 */
public class QuickSort {
    public static void main(String[] args) {
        AlgorithmUtils<Integer> algorithmUtils = new AlgorithmUtils();
        Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 54, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};
        algorithmUtils.print(array);
        quick(array);
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
            System.out.format("a[low] = a[high]  ---  low:%s,high:%s,a[low]:%s,a[high]:%s", low, high, a[low], a[high]);
            System.out.println();
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low++;
            }
            System.out.format("a[high] = a[low]  ---  low:%s,high:%s,a[high]:%s,a[low]:%s", low, high, a[high], a[low]);
            System.out.println();
            a[high] = a[low];
        }
        //找到基准元素位置，赋值
        System.out.format("a[low] = temp;  ---  low:%s,high:%s,a[low]:%s,temp:%s", low, high, a[low], temp);
        System.out.println();
        a[low] = temp;
        return low;
    }


}
