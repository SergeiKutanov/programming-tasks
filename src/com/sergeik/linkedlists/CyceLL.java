package com.sergeik.linkedlists;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again
 * by continuously following the next pointer. Internally, pos is used to denote the index of the node that
 * tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * -----------------------
 *
 * Can be done using hash table:
 * iterate over the list putting each node into the table as key
 * on each next node check if such key exists in the table
 * This will require additional memory but the loop will be detected at the first occurence
 *
 * -----------------------
 * Use Floyd's cycle detection algorithm
 *  - https://en.wikipedia.org/wiki/Cycle_detection
 *
 */
public class CycleLL {

    public static void main(String[] args) {
        ListNode head;

        head = ListHelper.buildFromArray(new int[] {1, 2, 3, 4});
        ListNode last = ListHelper.getNth(head, 3);
        ListNode second = ListHelper.getNth(head, 1);
        last.next = second;
        assert solution(head);

        head = ListHelper.buildFromArray(new int[] {1, 2});
        last = ListHelper.getNth(head, 1);
        second = ListHelper.getNth(head, 0);
        last.next = second;
        assert solution(head);

        head = ListHelper.buildFromArray(new int[] {1});
        assert !solution(head);

        head = ListHelper.buildFromArray(new int[] {});
        assert !solution(head);
    }

    /**
     * Using Floyd's cycle finding algorithm
     * @param head
     * @return
     */
    private static boolean solution(ListNode head) {

        if (head == null)
            return false;

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null) {
            if (fast == slow)
                return true;
            if (fast.next == null || fast.next.next == null) {
                return false;
            } else {
                fast = fast.next.next;
            }
            slow = slow.next;
        }
        return false;
    }

}
