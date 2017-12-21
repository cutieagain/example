package com.cutie.algorithm.sort;

/**
 * Created by cutie on 2017/12/10.
 */
public class ShellSort {
    public static void main(String[] args) {
        AlgorithmUtils<Integer> algorithmUtils = new AlgorithmUtils();
        Integer[] array = new Integer[]{2, 3, 5, 5, 42, 4, 52, 2, 52, 542, 43, 24, 3, 52, 52, 34, 2};
        array = shellSortMethod(array);
        algorithmUtils.print(array);
    }

    static Integer[] shellSortMethod(Integer[] array) {
        //确定gap
        int gap = array.length / 2;
        //根据gap分组插入排序,gap为多少就是分为几组
        while (gap > 0) {
            for (int i = 0; i < gap; i++) {
                for (int j = i+gap; j <= array.length-gap; j += gap) {
                    for (int k = j-gap; k >= 0 ; k -=gap) {
                        if(array[k+gap] < array[k]){
                            int temp = array[k+gap];
                            array[k+gap] = array[k];
                            array[k] = temp;
                        }else{
                            break;
                        }
                    }
                }
            }
            gap /= 2;
        }
        //到最后gap == 1，直接插入排序成功，排序成功
        return array;
    }
}
