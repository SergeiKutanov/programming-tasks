package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 */
public class RemoveNthNodeFromEndList {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {},
                ListHelper.toArray(
                        solution(ListHelper.buildFromArray(new int[]{1}), 1)
                )
        );
        assert Arrays.equals(
                new int[] {1,2,3,5},
                ListHelper.toArray(
                        solution(ListHelper.buildFromArray(new int[]{1,2,3,4,5}), 2)
                )
        );
    }

    private static ListNode solution(ListNode head, int n) {
        ListNode cent = new ListNode(0);
        cent.next = head;
        advance(cent, 0, n);
        return cent.next;
    }

    private static int advance(ListNode node, int current, int nth) {
        int sum = current;
        if (node.next != null) {
            sum = advance(node.next, current + 1, nth);
        }
        if (current + nth == sum) {
            if (node.next != null) {
                node.next = node.next.next;
            }
        }
        return sum;
    }

    private static ListNode iterativeSolution(ListNode head, int n) {
        ListNode cent = new ListNode(0);
        cent.next = head;

        int idx = 0;
        ListNode fast = head;
        while (idx++ < n && fast != null)
            fast = fast.next;

        if (idx < n)
            return cent.next;

        ListNode nthNode = head;
        ListNode prev = cent;
        while (fast != null) {
            fast = fast.next;
            nthNode = nthNode.next;
            prev = prev.next;
        }
        prev.next = nthNode.next;


        return cent.next;
    }

}
