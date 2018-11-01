package com.cutie.leetcode;

/**
 * Created by cutie on 2018/11/1.
 * <p>
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * Example:
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 */
public class _24_swap_node_in_pairs {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode swapPairs1(ListNode head) {
            ListNode dmy = new ListNode(0);
            ListNode preNode = dmy;
            preNode.next = head;
            //preNode -1- curNode -2- secNode -3- tempNode
            while (preNode.next != null && preNode.next.next != null) {
                ListNode curNode = preNode.next;//1
                ListNode secNode = curNode.next;//2
                ListNode tempNode = secNode.next;//3

                preNode.next = secNode;//2
                secNode.next = curNode;//1
                curNode.next = tempNode;//3

                preNode = curNode;
            }
            return dmy.next;
        }

        public ListNode swapPairs2(ListNode head) {
            if (head == null || head.next == null){
                return head;
            }
            ListNode suc = head.next;//2
            head.next = swapPairs2(head.next.next);//3
            suc.next = head;//1
            return suc;
        }

        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null){
                return head;
            }
            ListNode cur = head;
            ListNode newHead = head.next;
            while (cur != null && cur.next != null) {
                ListNode tmp = cur;//零时保存一下1
                cur = cur.next;//2赋值给1
                tmp.next = cur.next;//2的下一个给1的下一个
                cur.next = tmp;//2指向1
                cur = tmp.next;//3替换cur 下一次的循环
                if (cur != null && cur.next != null) {//3、4不为空
                    tmp.next = cur.next;//给下面的接上
                }
            }
            return newHead;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        outPut(listNode1);
        ListNode result = new Solution().swapPairs(listNode1);
        outPut(result);
    }

    //输出
    private static void outPut(ListNode tempNode){
        while (tempNode != null){
            if (tempNode.next != null){
                System.out.print(tempNode.val + "->" );
            }else{
                System.out.print(tempNode.val);
            }
            tempNode = tempNode.next;
        }
        System.out.println();
    }

}
