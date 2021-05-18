package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only
 * distinct numbers from the original list. Return the linked list sorted as well.
 */
public class RemoveDuplicatesFromSortedListII {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{2,3},
                ListHelper.toArray(
                        solution(ListHelper.buildFromArray(new int[]{1,1,1,2,3}))
                )
        );

        assert Arrays.equals(
                new int[]{1,2,5},
                ListHelper.toArray(
                        solution(ListHelper.buildFromArray(new int[]{1,2,3,3,4,4,5}))
                )
        );
    }

    private static ListNode solution(ListNode head) {
        ListNode cur = head;
        ListNode cent = new ListNode(1);
        cent.next = head;
        ListNode prev = cent;
        while (cur != null && cur.next != null) {
            if (cur.val != cur.next.val) {
                prev = cur;
                cur = cur.next;
            } else {
                int val = cur.val;
                ListNode next = cur.next;
                while (next != null && val == next.val)
                    next = next.next;
                prev.next = next;
                cur = prev.next;
            }
        }
        return cent.next;
    }

}
