package com.sergeik.linkedlists;

import com.sergeik.trees.TreeHelper;
import com.sergeik.trees.TreeNode;
import com.sergeik.utils.Compare;

/**
 * Given the head of a singly linked list where elements are sorted
 * in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
 * subtrees of every node never differ by more than 1.
 */
public class SortedListToBST {

    public static void main(String[] args) {
        TreeNode root = solution(ListHelper.buildFromArray(new int[]{-10,-3,0,5,9}));
        Object[] result = new Object[7];
        TreeHelper.toArray(root, 0, result);
        Object[] expected = new Object[]{0,-3,9,-10,null,5,null};
        assert Compare.compareObjArr(expected, result);
    }

    private static TreeNode solution(ListNode head) {
        if (head == null)
            return null;
        TreeNode root = buildTree(head);
        return root;
    }

    private static TreeNode buildTree(ListNode head) {
        ListNode middle = getMiddle(head);
        TreeNode root = new TreeNode(middle.val);
        if (!head.equals(middle))
            root.left = buildTree(head);
        if (middle.next != null)
            root.right = buildTree(middle.next);
        return root;
    }

    private static ListNode getMiddle(ListNode head) {
        ListNode hare = head;
        ListNode tort = head;
        ListNode beforeMiddle = head;
        while (hare != null && hare.next != null) {
            hare = hare.next.next;
            beforeMiddle = tort;
            tort = tort.next;
        }
        beforeMiddle.next = null;
        return tort;
    }

}
