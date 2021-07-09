package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Given a non-negative integer represented as a linked list of digits, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOneLinkedList {

    public static void main(String[] args) {
        ListNode head, exp;

        head = ListHelper.buildFromArray(new int[] {9,9,9});
        exp = ListHelper.buildFromArray(new int[] {1,0,0,0});
        assert Arrays.equals(
                ListHelper.toArray(exp),
                ListHelper.toArray(solution(head))
        );

        head = ListHelper.buildFromArray(new int[] {1,2,9});
        exp = ListHelper.buildFromArray(new int[] {1,3,0});
        assert Arrays.equals(
                ListHelper.toArray(exp),
                ListHelper.toArray(solution(head))
        );

        head = ListHelper.buildFromArray(new int[] {1,2,3});
        exp = ListHelper.buildFromArray(new int[] {1,2,4});
        assert Arrays.equals(
                ListHelper.toArray(exp),
                ListHelper.toArray(solution(head))
        );
    }

    private static ListNode solution(ListNode head) {
        int val = dfs(head);
        if (val == 1) {
            ListNode tmp = head;
            head = new ListNode(1);
            head.next = tmp;
        }
        return head;
    }

    private static int dfs(ListNode node) {
        if (node.next == null) {
            node.val++;
            if (node.val == 10) {
                node.val = 0;
                return 1;
            }
            return 0;
        }
        int next = dfs(node.next);
        node.val += next;
        if (node.val == 10) {
            node.val = 0;
            return 1;
        }
        return 0;
    }

}
