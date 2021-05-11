package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers 
 * and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{7,0,8},
                ListHelper.toArray(solution(
                        ListHelper.buildFromArray(new int[]{2,4,3}),
                        ListHelper.buildFromArray(new int[]{5,6,4})
                ))
        );
    }

    private static ListNode solution(ListNode l1, ListNode l2) {
        int carryOver = 0;
        ListNode cent = new ListNode(1);
        ListNode newHead = cent;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carryOver;
            if (sum > 9) {
                carryOver = sum / 10;
                sum %= 10;
            } else {
                carryOver = 0;
            }
            cent.next = new ListNode(sum);
            cent = cent.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode leftOver = l1 == null ? l2 : l1;
        while (leftOver != null) {
            int sum = leftOver.val + carryOver;
            if (sum > 9) {
                carryOver = sum / 10;
                sum %= 10;
            } else {
                carryOver = 0;
            }
            cent.next = new ListNode(sum);
            cent = cent.next;
            leftOver = leftOver.next;
        }
        if (carryOver > 0)
            cent.next = new ListNode(carryOver);

        return newHead.next;
    }

}
