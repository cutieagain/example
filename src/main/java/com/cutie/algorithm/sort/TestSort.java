package com.cutie.algorithm.sort;

/**
 * Created by Cutie on 2018/1/10.
 */
public class TestSort {
    public static void main(String[] args) {
        AlgorithmUtils<Integer> algorithmUtils = new AlgorithmUtils();
        Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 54, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};
//        Integer[] array = new Integer[]{3,5,4,3};
        algorithmUtils.print(array);
        quickSort(array);
        algorithmUtils.print(array);
    }

    /*快速排序*/
    private static void quickSort(Integer[] array) {
        if(array.length>0){
            recursiveQuickSort(array, 0, array.length-1);
        }
    }

    private static void recursiveQuickSort(Integer[] array, int low, int high) {
        //当low等于或者大于high的时候，退出revursive
        if(low<high){
            int middle = getMiddle(array, low, high);
            recursiveQuickSort(array, 0, middle-1);
            recursiveQuickSort(array, middle+1, high);
        }
    }

    private static int getMiddle(Integer[] array, int low, int high) {
        int temp = array[low];
        while(low<high){
            while(low<high&& array[high]>=temp){
                high--;
            }
            array[low] = array[high];
            while(low<high&& array[low]<=temp){
                low++;
            }
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }
}
