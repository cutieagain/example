package com.cutie.leetcode.arrays_linkedlist;

import com.cutie.leetcode.arrays_linkedlist._24_swap_node_in_pairs.*;

import static com.cutie.leetcode.arrays_linkedlist._24_swap_node_in_pairs.getInitNodeList;
import static com.cutie.leetcode.arrays_linkedlist._24_swap_node_in_pairs.outPut;

/**
 * Created by cutie on 2018/11/1.
 *
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class _206_reverse_linked_list {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode newHead = null;
            while (head != null){
                ListNode nextNode = head.next;
                head.next = newHead;
                //move
                newHead = head;
                head = nextNode;
            }
            return newHead;
        }
    }

    public static void main(String[] args) {
        ListNode headNode = getInitNodeList();
        outPut(headNode);
        ListNode result = new _206_reverse_linked_list.Solution().reverseList(headNode);
        outPut(result);
    }
}
