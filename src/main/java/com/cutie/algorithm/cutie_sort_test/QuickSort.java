package com.cutie.algorithm.cutie_sort_test;

/*
 * 快速排序：
 * 一趟快速排序的算法是： 　　
 * 1）设置两个变量i、j，排序开始的时候：i=0，j=N-1； 　　
 * 2）以第一个数组元素作为关键数据，赋值给key，即 key=A[0]； 　　
 * 3）从j开始向前搜索，即由后开始向前搜索（j=j-1即j--），
 * 找到第一个小于key的值A[j]，A[i]与A[j]交换； 　　
 * 4）从i开始向后搜索，即由前开始向后搜索（i=i+1即i++），
 * 找到第一个大于key的A[i]，A[i]与A[j]交换； 　　
 * 5）重复第3、4、5步，直到 I=J；
 * (3,4步是在程序中没找到时候j=j-1，i=i+1，直至找到为止。
 * 找到并交换的时候i， j指针位置不变。
 * 另外当i=j这过程一定正好是i+或j-完成的最后令循环结束。）
 */
public class QuickSort {
	public static void sort(int[] data) {
		quickSort(data, 0, data.length - 1);
	}

	private static void quickSort(int[] data, int low, int high) {
		if(low<high){
			int middle = getMiddle(data,low, high);
			quickSort(data, low, middle-1);
			quickSort(data, middle+1, high);

		}
	}

	private static int getMiddle(int[] data, int low, int high) {
		int temp = data[low];
		while(low<high){
			while (low<high && data[high]>=temp){
				high--;
			}
			data[low] = data[high];
			while (low<high && data[low]<=temp){
				low++;
			}
			data[high] = data[low];
		}
		data[low] = temp;
		return low;
	}


}
