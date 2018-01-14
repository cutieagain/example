package com.cutie.algorithm.cutie_sort_test;


/*
 * 选择排序基本思路：
 * 把第一个元素依次和后面的所有元素进行比较。
 * 第一次结束后，就会有最小值出现在最前面。
 * 依次类推
 */
public class SelectionSort {
	public static void sort(int[] data) {
		for (int i = 0; i <data.length ; i++) {
			for (int j = i+1; j < data.length; j++) {
				if(data[i]>data[j]){
					SortTest.swap(data,i,j);
				}
			}
		}
	}
}