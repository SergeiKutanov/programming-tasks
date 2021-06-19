package com.sergeik.trees;

/**
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the
 * same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants.
 * The tree tree could also be considered as a subtree of itself.
 */
public class SubtreeOfAnotherTree {

    public static void main(String[] args) {
        TreeNode root, subRoot;

        root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(2);
        subRoot = new TreeNode(3);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);
        assert !isSubtree(root, subRoot);

        root = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        assert isSubtree(root, subRoot);

        root.left.right.left = new TreeNode(0);
        assert !isSubtree(root, subRoot);
    }

    private static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null)
            return false;
        if (dfs(root, subRoot))
            return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null ^ subRoot == null)
            return false;
        if (root == null)
            return true;
        if (root.val != subRoot.val)
            return false;
        return dfs(root.left, subRoot.left) && dfs(root.right, subRoot.right);
    }

}
