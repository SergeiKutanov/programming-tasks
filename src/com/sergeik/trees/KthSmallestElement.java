package com.sergeik.trees;

/**
 * Given the root of a binary search tree, and an integer k, 
 * return the kth (1-indexed) smallest element in the tree.
 *
 *
 */
public class KthSmallestElement {

    private static int count = 0;
    private static int result = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);

        assert 3 == solution(root, 3);

    }

    private static int solution(TreeNode root, int k) {
        count = 0;
        result = Integer.MIN_VALUE;
        inOrder(root, k);
        return result;
    }

    private static void inOrder(TreeNode root, int k) {
        if (root == null)
            return;
        inOrder(root.left, k);
        count++;
        if (k == count) {
            result = root.val;
            return;
        }
        inOrder(root.right, k);
    }

}
