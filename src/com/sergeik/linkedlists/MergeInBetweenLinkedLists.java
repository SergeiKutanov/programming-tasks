package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 *
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 *
 * The blue edges and nodes in the following figure incidate the result:
 */
public class MergeInBetweenLinkedLists {

    public static void main(String[] args) {
        ListNode list1, list2, res;
        list1 = ListHelper.buildFromArray(new int[] {0,1,2,3,4,5});
        list2 = ListHelper.buildFromArray(new int[] {100, 101, 102});
        res = solution(list1, 3,4, list2);
        assert Arrays.equals(new int[] {0,1,2,100,101,102,5}, ListHelper.toArray(res));
    }

    private static ListNode solution(ListNode list1, int a, int b, ListNode list2) {
        ListNode cent = new ListNode(0);
        ListNode newHead = cent;
        cent.next = list1;
        b -= a;
        while (a-- > 0) {
            cent = cent.next;
        }
        ListNode breakPoint = cent;
        while (b-- >= 0) {
            cent = cent.next;
        }
        breakPoint.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = cent.next;
        return newHead.next;
    }

}
