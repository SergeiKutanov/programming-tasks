package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less
 * than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class PartitionList {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{1,2,2,4,3,5},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{1,4,3,2,5,2}),
                        3
                ))
        );
    }

    private static ListNode solution(ListNode head, int x) {
        ListNode newHead = new ListNode(-1);
        ListNode firstRunner = newHead;
        ListNode secondHead = new ListNode(-1);
        ListNode secondRunner = secondHead;
        while (head != null) {
            if (head.val < x) {
                firstRunner.next = head;
                firstRunner = firstRunner.next;
            } else {
                secondRunner.next = head;
                secondRunner = secondRunner.next;
            }
            head = head.next;
        }
        secondRunner.next = null;
        firstRunner.next = secondHead.next;
        return newHead.next;
    }

}
