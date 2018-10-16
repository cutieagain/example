package com.cutie.leetcode;

/**
 * unsolved
 *
 * Created by cutie on 2018/3/1.
 *
 *
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class _2_add_two_numbers {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int extraOne = 0;
        ListNode listNode = new ListNode(0);
        while(l1!=null || l2!=null){
            listNode.val = l1.val + l2.val + extraOne;
            listNode.val = listNode.val % 10;
            extraOne = listNode.val / 10 == 1 ? 1 : 0;
            listNode.next = new ListNode(0);
            listNode = listNode.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        return listNode;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 = new ListNode(0);

    }

}
