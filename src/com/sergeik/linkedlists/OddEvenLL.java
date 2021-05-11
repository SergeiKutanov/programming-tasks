package com.sergeik.linkedlists;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed
 * by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 */
public class OddEvenLL {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{1},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{1})
                ))
        );
        assert Arrays.equals(
                new int[]{},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{})
                ))
        );
        assert Arrays.equals(
                new int[]{2,3,6,1,5,4},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{2,1,3,5,6,4})
                ))
        );
        assert Arrays.equals(
                new int[]{2,3,6,7,1,5,4},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{2,1,3,5,6,4,7})
                ))
        );
    }

    private static ListNode solution(ListNode head) {
        ListNode cent = new ListNode(-1);
        cent.next = head;
        if (head == null)
            return null;
        ListNode evensStart = head.next;
        ListNode oddsHead = head;
        ListNode evensHead = head.next;
        while (oddsHead != null && evensHead != null && oddsHead.next.next != null && evensHead.next.next != null) {
            ListNode tmpNextOdd = oddsHead.next.next;
            ListNode tmpEvenOdd = evensHead.next.next;
            oddsHead.next = oddsHead.next.next;
            evensHead.next = evensHead.next.next;
            oddsHead = tmpNextOdd;
            evensHead = tmpEvenOdd;
        }

        //odd case
        if (evensHead != null && evensHead.next != null) {
            oddsHead.next = oddsHead.next.next;
            evensHead.next = null;
            oddsHead = oddsHead.next;
        }
        oddsHead.next = evensStart;
        return cent.next;
    }

    private static ListNode solutionWithQueue(ListNode head) {
        ListNode cent = new ListNode(-1);
        cent.next = head;
        ListNode newHead = cent;
        int count = 0;
        Queue<ListNode> odds = new LinkedList<>();
        Queue<ListNode> evens = new LinkedList<>();
        while (head != null) {
            count++;
            if (count % 2 > 0)
                odds.offer(head);
            else
                evens.offer(head);
            head = head.next;
        }

        while (!odds.isEmpty()) {
            cent.next = odds.poll();
            cent = cent.next;
        }
        while (!evens.isEmpty()) {
            cent.next = evens.poll();
            cent = cent.next;
        }
        cent.next = null;

        return newHead.next;
    }

}
