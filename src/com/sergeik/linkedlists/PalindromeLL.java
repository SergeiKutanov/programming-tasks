package com.sergeik.linkedlists;

import java.util.Stack;

public class PalindromeLL {

    public static void main(String[] args) {
        ListNode head;
        head = ListHelper.buildFromArray(new int[] {1, 2, 2, 1});
        assert stackSolution(head);
        assert stringBuilderSolution(head);

        head = ListHelper.buildFromArray(new int[] {1, 2});
        assert !stackSolution(head);
        assert !stringBuilderSolution(head);

        head = ListHelper.buildFromArray(new int[] {});
        assert stackSolution(head);
        assert stringBuilderSolution(head);


        //two pointers approach modifies the list so re-create it
        head = ListHelper.buildFromArray(new int[] {1, 2, 2, 1});
        assert twoPointersSolution(head);

        head = ListHelper.buildFromArray(new int[] {1, 2});
        assert !twoPointersSolution(head);

        head = ListHelper.buildFromArray(new int[] {});
        assert twoPointersSolution(head);
    }

    private static boolean twoPointersSolution(ListNode head) {
        //put slow pointer in middle
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                fast = fast.next;
            }
            slow = slow.next;
        }

        //reverse second half
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        //compare the reversed half with the original
        while (prev != null) {
            if (prev.val != head.val)
                return false;
            prev = prev.next;
            head = head.next;
        }
        return true;
    }

    private static boolean stringBuilderSolution(ListNode head) {
        StringBuilder str = new StringBuilder();
        ListNode node = head;
        while (node != null) {
            str.append(node.val);
            node = node.next;
        }
        String originalHalf = str.substring(0, str.length());
        String reversedHalf = str.reverse().substring(0, str.length());
        return originalHalf.equals(reversedHalf);
    }

    private static boolean stackSolution(ListNode head) {

        Stack<Integer> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }

        int center = stack.size() / 2;
        node = head;
        for (int i = 0; i < center; i++) {
            if (node.val != stack.pop())
                return false;
            node = node.next;
        }

        return true;
    }

}
