package com.cutie.leetcode.arrays_linkedlist;

import com.cutie.leetcode.arrays_linkedlist._24_swap_node_in_pairs.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by cutie on 2018/11/2.
 */
/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        /*ListNode cur = new ListNode(0);
        ListNode result = cur;
        cur.next = head;
        Set filter = new HashSet();
        ListNode temp;
        while(head != null){
            if(!filter.contains(head.val)){//如果不包含
                filter.add(head.val);
                temp = head.next;
                cur.next = head;
                cur = head;
                head = temp;
            }else{//如果包含
                temp = head.next;
                head = temp;
            }
        }
        cur.next = head;
        return result.next;*/

        ListNode cur = new ListNode(0);
        ListNode result = cur;
        cur.next = head;
        Set filter = new HashSet();
        while(head != null && head.next != null){
            if(!filter.contains(head.val)){//如果不包含 cur指向head
                filter.add(head.val);
                cur.next = head;
                cur = head;
                head = head.next;
            }else{//如果包含 cur指向head的下一个，如果head的下一个不是空的话
                head = head.next;
            }
        }
        return result.next;
    }
}

public class _83_remove_duplicate_from_sorted_list {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            ListNode ret = new Solution().deleteDuplicates(head);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}