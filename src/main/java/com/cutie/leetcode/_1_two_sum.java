package com.cutie.leetcode;

/**
 * Created by Administrator on 2017/11/23.
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class _1_two_sum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(twoSum(nums, target).toString());
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] response = new int[2];
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                int result = nums[i]+nums[j];
                if(result==target){
                    response[0] = i;
                    response[1] = j;
                }
            }
        }
        return response;
    }
}
