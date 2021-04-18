package com.sergeik.linkedlists;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLL {

    public static void main(String[] args) {
        //using stack
        ListNode head = ListHelper.buildFromArray(new int[] {1, 2, 3, 4, 5});
        head = solution(head);
        assert Arrays.equals(new int[] {5, 4, 3, 2, 1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {1, 2});
        head = solution(head);
        assert Arrays.equals(new int[] {2, 1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {});
        head = solution(head);
        assert Arrays.equals(new int[] {}, ListHelper.toArray(head));

        //iterative
        head = ListHelper.buildFromArray(new int[] {1, 2, 3, 4, 5});
        head = iterativeSolution(head);
        assert Arrays.equals(new int[] {5, 4, 3, 2, 1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {1, 2});
        head = iterativeSolution(head);
        assert Arrays.equals(new int[] {2, 1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {});
        head = iterativeSolution(head);
        assert Arrays.equals(new int[] {}, ListHelper.toArray(head));

        //recursive
        head = ListHelper.buildFromArray(new int[] {1, 2, 3, 4, 5});
        head = recursiveSolution(head);
        assert Arrays.equals(new int[] {5, 4, 3, 2, 1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {1, 2});
        head = recursiveSolution(head);
        assert Arrays.equals(new int[] {2, 1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {});
        head = recursiveSolution(head);
        assert Arrays.equals(new int[] {}, ListHelper.toArray(head));
    }

    /**
     * Time O(n)
     * Space O(1)
     * @param head
     * @return
     */
    private static ListNode iterativeSolution(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        return prev;
    }

    /**
     * Time O(n)
     * Space O(n) - recursion
     * @param head
     * @return
     */
    private static ListNode recursiveSolution(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = recursiveSolution(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * Time O(n)
     * Space O(n)
     * @param head
     * @return
     */
    private static ListNode solution(ListNode head) {
        Stack<Integer> nodeStack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            nodeStack.push(node.val);
            node = node.next;
        }
        node = head;
        while (node != null) {
            node.val = nodeStack.pop();
            node = node.next;
        }
        return head;
    }

}
