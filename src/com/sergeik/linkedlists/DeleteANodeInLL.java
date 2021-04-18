package com.sergeik.linkedlists;

import java.util.Arrays;

/**
 * Write a function to delete a node in a singly-linked list. You will not be given access to the head of the list,
 * instead you will be given access to the node to be deleted directly.
 *
 * It is guaranteed that the node to be deleted is not a tail node in the list.
 */
public class DeleteANodeInLL {

    public static void main(String[] args) {

        ListNode head = ListHelper.buildFromArray(new int[] {4, 5, 1, 9});
        deleteNode(ListHelper.getNth(head, 1));
        assert Arrays.equals(new int[] {4, 1, 9}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {4, 5, 1, 9});
        deleteNode(ListHelper.getNth(head, 2));
        assert Arrays.equals(new int[] {4, 5, 9}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {0, 1});
        deleteNode(ListHelper.getNth(head, 0));
        assert Arrays.equals(new int[] {1}, ListHelper.toArray(head));

        head = ListHelper.buildFromArray(new int[] {-3, 5, -99});
        deleteNode(ListHelper.getNth(head, 1));
        assert Arrays.equals(new int[] {-3, -99}, ListHelper.toArray(head));

    }

    private static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
