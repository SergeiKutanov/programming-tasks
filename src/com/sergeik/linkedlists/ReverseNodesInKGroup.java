package com.sergeik.linkedlists;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes
 * is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode head;
        int[] exp;

        head = ListHelper.buildFromArray(new int[] {1,2,3,4,5});
        exp = new int[] {2,1,4,3,5};
        assert Arrays.equals(exp, ListHelper.toArray(reverseKGroup(head, 2)));

        head = ListHelper.buildFromArray(new int[] {1,2,3,4,5});
        exp = new int[] {1,2,3,4,5};
        assert Arrays.equals(exp, ListHelper.toArray(reverseKGroup(head, 1)));

        head = ListHelper.buildFromArray(new int[] {1});
        exp = new int[] {1};
        assert Arrays.equals(exp, ListHelper.toArray(reverseKGroup(head, 1)));
    }

    private static ListNode stackSolution(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cent = new ListNode(0);
        cent.next = head;
        ListNode node = head, prev = cent;
        while (node != null) {
            int count = 0;
            while (node != null && count < k) {
                count++;
                stack.push(node);
                node = node.next;
            }
            if (count == k) {
                while (!stack.isEmpty()) {
                    prev.next = stack.pop();
                    prev = prev.next;
                }
                prev.next = node;
            }
        }
        return cent.next;
    }

    private static ListNode reverseKGroup(ListNode head, int k){
        int count = 0;
        ListNode ptr = head;

        // First, see if there are atleast k nodes
        // left in the linked list.
        while (count < k && ptr != null) {
            ptr = ptr.next;
            count++;
        }


        // If we have k nodes, then we reverse them
        if (count == k) {

            // Reverse the first k nodes of the list and
            // get the reversed list's head.
            ListNode reversedHead = reverseLinkedList(head, k);

            // Now recurse on the remaining linked list. Since
            // our recursion returns the head of the overall processed
            // list, we use that and the "original" head of the "k" nodes
            // to re-wire the connections.
            head.next = reverseKGroup(ptr, k);
            return reversedHead;
        }

        return head;
    }

    private static ListNode reverseLinkedList(ListNode head, int k) {

        // Reverse k nodes of the given linked list.
        // This function assumes that the list contains
        // atleast k nodes.
        ListNode new_head = null;
        ListNode ptr = head;

        while (k > 0) {

            // Keep track of the next node to process in the
            // original list
            ListNode next_node = ptr.next;

            // Insert the node pointed to by "ptr"
            // at the beginning of the reversed list
            ptr.next = new_head;
            new_head = ptr;

            // Move on to the next node
            ptr = next_node;

            // Decrement the count of nodes to be reversed by 1
            k--;
        }


        // Return the head of the reversed list
        return new_head;

    }

}
