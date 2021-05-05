package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class MergeKSortedLists {

    public static void main(String[] args) {

        ListNode list1Head = new ListNode(1);
        list1Head.next = new ListNode(4);
        list1Head.next.next = new ListNode(5);

        ListNode list2Head = new ListNode(1);
        list2Head.next = new ListNode(3);
        list2Head.next.next = new ListNode(4);

        ListNode list3Head = new ListNode(2);
        list3Head.next = new ListNode(6);

        assert Arrays.equals(
                new int[] {1,1,2,3,4,4,5,6},
                ListHelper.toArray(solution(new ListNode[]{list1Head, list2Head, list3Head}))
        );

        assert Arrays.equals(new int[]{}, ListHelper.toArray(solution(new ListNode[]{})));
        assert Arrays.equals(new int[]{}, ListHelper.toArray(solution(new ListNode[]{null})));

    }

    private static ListNode solution(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        return mergeLists(lists, 0, lists.length);
    }

    private static ListNode mergeLists(ListNode[] lists, int start, int end) {
        if (end - start == 1) {
            return lists[start];
        }
        int mid = (start + end) / 2;

        ListNode left = mergeLists(lists, start, mid);
        ListNode right = mergeLists(lists, mid, end);
        ListNode mergedHead = new ListNode(Integer.MIN_VALUE);
        ListNode mergedNext = mergedHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                mergedNext.next = new ListNode(left.val);
                left = left.next;
            } else {
                mergedNext.next = new ListNode(right.val);
                right = right.next;
            }
            mergedNext = mergedNext.next;
        }

        if (left != null) {
            mergedNext.next = left;
        }
        if (right != null) {
            mergedNext.next = right;
        }

        return mergedHead.next;
    }
}
