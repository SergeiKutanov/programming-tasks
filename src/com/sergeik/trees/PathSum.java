package com.sergeik.trees;

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf
 * path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 */
public class PathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        assert hasPathSum(root, 22);

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assert !hasPathSum(root, 5);
    }

    private static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.val == targetSum && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }

}
