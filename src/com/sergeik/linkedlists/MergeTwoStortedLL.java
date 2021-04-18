package com.sergeik.linkedlists;

import java.util.Arrays;

public class MergeTwoSortedLL {

    public static void main(String[] args) {
        ListNode l1 = ListHelper.buildFromArray(new int[]{1, 2, 4});
        ListNode l2 = ListHelper.buildFromArray(new int[]{1, 3, 4});
        ListNode head = solution(l1, l2);
        assert Arrays.equals(new int[]{1, 1, 2, 3, 4, 4}, ListHelper.toArray(head));

        l1 = ListHelper.buildFromArray(new int[]{2});
        l2 = ListHelper.buildFromArray(new int[]{1});
        head = solution(l1, l2);
        assert Arrays.equals(new int[]{1, 2}, ListHelper.toArray(head));

        l1 = ListHelper.buildFromArray(new int[]{});
        l2 = ListHelper.buildFromArray(new int[]{1});
        head = solution(l1, l2);
        assert Arrays.equals(new int[]{1}, ListHelper.toArray(head));


        l1 = ListHelper.buildFromArray(new int[]{1, 2, 4});
        l2 = ListHelper.buildFromArray(new int[]{1, 3, 4});
        head = recursiveSolution(l1, l2);
        assert Arrays.equals(new int[]{1, 1, 2, 3, 4, 4}, ListHelper.toArray(head));

        l1 = ListHelper.buildFromArray(new int[]{2});
        l2 = ListHelper.buildFromArray(new int[]{1});
        head = recursiveSolution(l1, l2);
        assert Arrays.equals(new int[]{1, 2}, ListHelper.toArray(head));

        l1 = ListHelper.buildFromArray(new int[]{});
        l2 = ListHelper.buildFromArray(new int[]{1});
        head = recursiveSolution(l1, l2);
        assert Arrays.equals(new int[]{1}, ListHelper.toArray(head));

    }

    private static ListNode solution(ListNode l1, ListNode l2) {
        /**
         * Iteratively compare values at current pointers in l1 and l2
         * pick the smallest and put it as head's next
         */
        if (l1 != null && l2 == null) return l1;
        if (l2 != null && l1 == null) return l2;
        if (l1 == null) return null;

        ListNode currentL1 = l1;
        ListNode currentL2 = l2;
        ListNode head;

        if (currentL1.val <= currentL2.val) {
            head = currentL1;
            currentL1 = currentL1.next;
        } else {
            head = currentL2;
            currentL2 = currentL2.next;
        }
        ListNode realHead = head;

        while (currentL1 != null && currentL2 != null) {
            if (currentL1.val <= currentL2.val) {
                head.next = currentL1;
                currentL1 = currentL1.next;
            } else {
                head.next = currentL2;
                currentL2 = currentL2.next;
            }
            head = head.next;
        }

        if (currentL1 != null) {
            head.next = currentL1;
        }
        if (currentL2 != null) {
            head.next = currentL2;
        }
        return realHead;
    }

    private static ListNode recursiveSolution(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;

        else if(l2 == null)
            return l1;

        else if(l1.val < l2.val) {
            l1.next = recursiveSolution(l1.next, l2);
            return l1;
        }

        else {
            l2.next = recursiveSolution(l1, l2.next);
            return l2;
        }
    }

}
