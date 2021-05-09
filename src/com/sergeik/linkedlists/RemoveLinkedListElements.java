package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list
 * that has Node.val == val, and return the new head.
 */
public class RemoveLinkedListElements {

    public static void main(String[] args) {
        assert Arrays.equals(
                ListHelper.toArray(
                        solution(ListHelper.buildFromArray(new int[]{1,2,6,3,4,5,6}), 6)
                ),
                new int[]{1,2,3,4,5}
        );
        assert Arrays.equals(
                ListHelper.toArray(
                        solution(ListHelper.buildFromArray(new int[]{1}), 1)
                ),
                new int[]{}
        );
        assert Arrays.equals(
                ListHelper.toArray(
                        solution(ListHelper.buildFromArray(new int[]{7,7,7,7}), 7)
                ),
                new int[]{}
        );
    }

    private static ListNode solution(ListNode head, int val) {
        ListNode cent = new ListNode(0);
        cent.next = head;
        ListNode prev = cent;
        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
            } else {
                prev = head;
            }
            head = head.next;
        }
        return cent.next;
    }

}
