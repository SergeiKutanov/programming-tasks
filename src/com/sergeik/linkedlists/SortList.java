package com.sergeik.linkedlists;


import java.util.Arrays;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortList {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {1,2,3,4},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{4,2,1,3})
                ))
        );
        assert Arrays.equals(
                new int[] {-1,0,3,4,5},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[] {-1,5,3,4,0})
                ))
        );
        assert Arrays.equals(
                new int[] {1,2,3,4},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[] {4,2,1,3})
                ))
        );
    }

    private static ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middle = getMiddle(head);
        ListNode left = solution(head);
        ListNode right = solution(middle);

        return mergeList(left, right);
    }

    private static ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode centenial = new ListNode(Integer.MIN_VALUE);
        ListNode head = new ListNode(Integer.MIN_VALUE);
        head.next = centenial;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                centenial.next = l1;
                l1 = l1.next;
            } else {
                centenial.next = l2;
                l2 = l2.next;
            }
            centenial = centenial.next;
        }

        centenial.next = l1 != null ? l1 : l2;
        return head.next.next;
    }

    private static ListNode getMiddle(ListNode head) {
        ListNode middle = null;
        while (head != null && head.next != null) {
            middle = middle != null ? middle.next : head;
            head = head.next.next;
        }
        ListNode beforeMiddle = middle.next;
        middle.next = null;
        return beforeMiddle;
    }

    private static ListNode mergeSort(ListNode head) {
        //find middle
        ListNode centenial = new ListNode(Integer.MIN_VALUE);
        centenial.next = head;
        int halfListSize = 0;
        ListNode hare = centenial.next;
        ListNode tortoise = centenial.next;
        ListNode middleNode = null;
        while (hare != null) {
            halfListSize++;
            middleNode = tortoise;
            tortoise = tortoise.next;
            if (hare.next != null) {
                hare = hare.next.next;
            } else {
                hare = hare.next;
            }
        }

        if (halfListSize == 1) {
            if (head.next != null && head.val > head.next.val) {
                int tmp = head.val;
                head.val = head.next.val;
                head.next.val = tmp;
            }
            return head;
        } else {
            middleNode.next = null;
            ListNode leftHead = mergeSort(centenial.next);
            ListNode rightHead = mergeSort(tortoise);

            ListNode newHead = new ListNode(Integer.MIN_VALUE);
            ListNode cent = new ListNode(Integer.MIN_VALUE);
            newHead.next = cent;
            while (leftHead != null && rightHead != null) {
                if (leftHead.val < rightHead.val) {
                    cent.next = leftHead;
                    leftHead = leftHead.next;
                } else {
                    cent.next = rightHead;
                    rightHead = rightHead.next;
                }
                cent = cent.next;
            }

            if (leftHead != null) {
                cent.next = leftHead;
            }
            if (rightHead != null) {
                cent.next = rightHead;
            }

            return newHead.next.next;
        }

    }

    private static ListNode bubleSolution(ListNode head) {
        boolean sorted = false;
        ListNode centenial = new ListNode(Integer.MIN_VALUE);
        centenial.next = head;
        while (!sorted) {
            sorted = true;
            head = centenial.next;
            while (head != null) {
                if (head.next != null && head.next.val < head.val) {
                    sorted = false;
                    int tmp = head.val;
                    head.val = head.next.val;
                    head.next.val = tmp;
                }
                head = head.next;
            }
        }
        return centenial.next;
    }

}
