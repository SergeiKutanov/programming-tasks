package com.sergeik.linkedlists;

/**
 * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
 *
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than one.
 * This may lead to some parts being null.
 *
 * The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a
 * size greater than or equal to parts occurring later.
 *
 * Return an array of the k parts.
 */
public class SplitLinkedListInParts {

    public static void main(String[] args) {
        ListNode[] res;
        res = solution(ListHelper.buildFromArray(new int[] {1,2,3}), 5);
    }

    private static ListNode[] solution(ListNode head, int k) {
        int listSize = 0;
        ListNode next = head;
        while (next != null) {
            listSize++;
            next = next.next;
        }
        int blockSize = listSize / k;
        int additional = listSize % k;
        ListNode[] res = new ListNode[k];
        next = head;
        int idx = 0;
        while (k-- > 0) {
            ListNode start = next;
            ListNode prev = null;
            for (int i = 0; i < blockSize; i++) {
                prev = next;
                next = next.next;
            }
            if (additional-- > 0) {
                prev = next;
                next = next.next;
            }
            if (prev != null) {
                prev.next = null;
                res[idx] = start;
            }
            idx++;
        }
        return res;
    }

}
