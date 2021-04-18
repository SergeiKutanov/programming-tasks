package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Follow up: Could you do this in one pass?
 */
public class DeleteNthFromEnd {

    public static void main(String[] args) {
        ListNode head = ListHelper.buildFromArray(new int[]{1, 2, 3, 4, 5});
        head = solution(head, 2);
        assert Arrays.equals(new int[]{1, 2, 3, 5}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[]{2, 1});
        head = solution(head, 2);
        assert Arrays.equals(new int[]{1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[]{1, 2});
        head = solution(head, 1);
        assert Arrays.equals(new int[]{1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[]{1});
        head = solution(head, 1);
        assert Arrays.equals(new int[]{}, ListHelper.toArray(head));



        head = ListHelper.buildFromArray(new int[]{1, 2, 3, 4, 5});
        head = twoPassSolution(head, 2);
        assert Arrays.equals(new int[]{1, 2, 3, 5}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[]{2, 1});
        head = twoPassSolution(head, 2);
        assert Arrays.equals(new int[]{1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[]{1, 2});
        head = twoPassSolution(head, 1);
        assert Arrays.equals(new int[]{1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[]{1});
        head = twoPassSolution(head, 1);
        assert Arrays.equals(new int[]{}, ListHelper.toArray(head));

    }

    private static ListNode twoPassSolution(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    /**
     * One pass solution
     * Create a dummy node with next pointing to head
     * advance first pointer to n+1
     * advance both pointers until first becomes null
     * update second.next <- second.next.next
     * @param head
     * @param n
     * @return
     */
    private static ListNode solution(ListNode head, int n) {
        ListNode prevHead = new ListNode(0);
        prevHead.next = head;
        ListNode first = prevHead;
        ListNode second = prevHead;

        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return prevHead.next;
    }

}
