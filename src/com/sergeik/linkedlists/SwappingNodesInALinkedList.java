package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning
 * and the kth node from the end (the list is 1-indexed).
 */
public class SwappingNodesInALinkedList {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[] {90, 100},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[] {100, 90}),
                        2
                ))
        );

        assert Arrays.equals(
                new int[] {2,1},
                ListHelper.toArray(
                        solution(ListHelper.buildFromArray(new int[] {1,2}), 1)
                )
        );

        assert Arrays.equals(
                new int[] {1},
                ListHelper.toArray(solution(new ListNode(1), 1))
        );

        assert Arrays.equals(
                new int[] {1,4,3,2,5},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[] {1,2,3,4,5}),
                        2
                ))
        );
    }

    private static ListNode solution(ListNode head, int k) {
        ListNode kthNodeFromEnd = head;
        ListNode runner = head;
        ListNode kthNode = head;
        int counter = 0;
        while (runner != null) {
            if (counter < k - 1)
                kthNode = kthNode.next;
            if (counter++ >= k)
                kthNodeFromEnd = kthNodeFromEnd.next;
            runner = runner.next;
        }

        int tmp = kthNode.val;
        kthNode.val = kthNodeFromEnd.val;
        kthNodeFromEnd.val = tmp;

        return head;
    }

    private static ListNode twoPassSolution(ListNode head, int k) {

        ListNode kthNodeFromEnd = head;
        ListNode runner = head;
        int counter = 0;
        while (runner != null) {
            if (counter++ >= k)
                kthNodeFromEnd = kthNodeFromEnd.next;
            runner = runner.next;
        }

        runner = head;
        counter = 0;
        while (counter < k - 1) {
            runner = runner.next;
            counter++;
        }


        int tmp = runner.val;
        runner.val = kthNodeFromEnd.val;
        kthNodeFromEnd.val = tmp;

        return head;
    }

}
