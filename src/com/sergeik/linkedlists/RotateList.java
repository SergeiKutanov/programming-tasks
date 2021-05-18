package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 */
public class RotateList {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {1},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{1}),
                        0
                ))
        );

        assert Arrays.equals(
                new int[] {2,0,1},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{0,1,2}),
                        4
                ))
        );

        assert Arrays.equals(
                new int[] {4,5,1,2,3},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{1,2,3,4,5}),
                        2
                ))
        );
    }

    private static ListNode solution(ListNode head, int k) {

        if (head == null)
            return null;

        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        int rotatePoint = k % length;
        if (rotatePoint == 0)
            return head;
        ListNode newHead = head;
        ListNode prev = null;
        int count = 0;
        while (count < length - rotatePoint) {
            prev = newHead;
            newHead = newHead.next;
            count++;
        }
        prev.next = null;

        node = newHead;
        while (count < length - 1) {
            node = node.next;
            count++;
        }
        node.next = head;
        return newHead;
    }

}
