package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the
 * nodes of the list from position left to position right, and return the reversed list.
 */
public class ReverseLinkedListII {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[]{5,3},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{3,5}),
                        1, 2
                ))
        );

        assert Arrays.equals(
                new int[]{5},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{5}),
                        1, 1
                ))
        );

        assert Arrays.equals(
                new int[]{1,4,3,2,5},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{1,2,3,4,5}),
                        2, 4
                ))
        );
    }

    private static ListNode solution(ListNode head, int left, int right) {
        if (head == null)
            return null;
        ListNode cent = new ListNode(0);
        cent.next = head;
        ListNode prev = cent;

        for (int i = 0; i < left - 1; i++)
            prev = prev.next;

        ListNode start = prev.next;
        ListNode then = start.next;

        /*
        1 - 2 - 3 - 4 - 5
        prev = 1
        start = 2
        next = 3
         */
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
        }

        //first: cent -> 1 -> 3 -> 2 -> 4 -> 5 : prev = 1; start = 2; then = 4

        return cent.next;
    }

}
