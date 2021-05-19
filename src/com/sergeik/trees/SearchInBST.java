package com.sergeik.trees;

/**
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
 * If such a node does not exist, return null.
 */
public class SearchInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(7);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        assert root.left.right.equals(solution(root, 3));
    }

    private static TreeNode solution(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val)
            return root;
        TreeNode left = solution(root.left, val);
        if (left != null)
            return left;
        return solution(root.right, val);
    }

}
