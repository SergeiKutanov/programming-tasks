package com.sergeik.trees;

import java.util.Arrays;
import java.util.List;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to
 * the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class FlattenBinaryTreeToLL {

    private static TreeNode head;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        solution(root);

        List<Integer> expected = Arrays.asList(1,2,3,4,5,6);
        int count = 0;
        while (root != null) {
            assert expected.get(count++).intValue() == root.val;
            root = root.right;
        }

    }

    private static void solution(TreeNode root) {
        head = null;
        if (root == null)
            return;
        revPreOrder(root);
    }

    private static void revPreOrder(TreeNode node) {
        if (node.right != null)
            revPreOrder(node.right);
        if (node.left != null)
            revPreOrder(node.left);
        node.left = null;
        node.right = head;
        head = node;
    }

}
