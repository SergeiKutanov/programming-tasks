package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without
 * modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {1},
                ListHelper.toArray(solution(ListHelper.buildFromArray(new int[]{1})))
        );
        assert Arrays.equals(
                new int[] {2,1,4,3},
                ListHelper.toArray(solution(ListHelper.buildFromArray(new int[]{1,2,3,4})))
        );
        assert Arrays.equals(
                new int[] {},
                ListHelper.toArray(solution(null))
        );
    }

    private static ListNode solution(ListNode head) {
        ListNode cent = new ListNode(-1);
        cent.next = head;

        ListNode prev = cent;
        while (head != null) {
            head = swap(prev, head);
            if (head.next != null) {
                prev = head.next;
                head = head.next.next;
            } else {
                return cent.next;
            }
        }

        return cent.next;
    }

    private static ListNode swap(ListNode prev, ListNode node) {
        if (node == null)
            return null;
        if (node.next == null)
            return node;
        ListNode tmp = node.next;
        node.next = node.next.next;
        tmp.next = node;
        prev.next = tmp;
        return tmp;
    }

}
