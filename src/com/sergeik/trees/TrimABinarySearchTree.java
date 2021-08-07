package com.sergeik.trees;

/**
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so
 * that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the
 * elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven
 * that there is a unique answer.
 *
 * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
 */
public class TrimABinarySearchTree {

    public static void main(String[] args) {
        TreeNode root, exp, res;

        root = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        res = trimBST(root, 1,2);

        root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);
        root.right = new TreeNode(4);
        res = trimBST(root, 1, 3);
        String s = "test";
    }

    private static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null)
            return null;
        if (root.val < low)
            return trimBST(root.right, low, high);
        if (root.val > high)
            return trimBST(root.left, low, high);
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    private static TreeNode solution(TreeNode root, int low, int high) {
        TreeNode res = dfs(root, low, high);
        return res;
    }

    private static TreeNode dfs(TreeNode node, int low, int high) {
        if (node == null)
            return null;
        node.left = dfs(node.left, low, high);
        node.right = dfs(node.right, low, high);
        if (node.val < low || node.val > high) {
            if (node.left != null && node.right != null)
                return node.left.val > node.right.val ? node.left : node.right;
            else if (node.left == null)
                return node.right;
            else
                return node.left;
        }
        return node;
    }

}
