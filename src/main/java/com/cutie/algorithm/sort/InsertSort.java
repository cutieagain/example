package com.cutie.algorithm.sort;

/**
 * Created by cutie on 2017/12/10.
 */
public class InsertSort {
    public static void main(String[] args) {
        AlgorithmUtils<Integer> algorithmUtils = new AlgorithmUtils();
        Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 52, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};
        algorithmUtils.print(array);
        divideInsertSort(array);
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

    /* cutie hand1 */
    static Integer[] insertSortMethod3(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            //array[j] > array[j+1]  之前写错写成这样了
            while (array[j] > temp && j != 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        return array;
    }

    /* cutie hand2 */
    static Integer[] insertSortMethod4(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            //为什么这个temp一定要提出来，哪里修改了他？
            int temp = array[i];
            for (int j = i - 1; j > 0; j--) {
                if (array[j] > temp) {
                    //这里修改了 array[j+1] 第一次的时候 == array[i]
                    array[j + 1] = array[j];
                } else {
                    array[j + 1] = temp;
                    break;
                }
            }
        }
        return array;
    }


    /*二分插排*/
    private static void divideInsertSort(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (temp < a[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            if (left != i) {
                a[left] = temp;
            }
        }
    }


    /*二分插排 cutie hand*/
    private static void divideInsertSort2(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int left = 0;
            int mid = i-1;
            int right = 0;
            while(left <= right){
                mid = (left+right)/2;
                if(a[mid]<temp){
                    left=mid+1;
                }else {
                    right=mid-1;
                }
            }
            for(int j=i-1; j>=left; j--){
                a[j+1] = a[j];
            }
            if (left != i) {
                a[left] = temp;
            }
        }
    }

}
