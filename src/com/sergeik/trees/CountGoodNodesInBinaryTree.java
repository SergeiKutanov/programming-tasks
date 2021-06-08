package com.sergeik.trees;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X
 * there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 */
public class CountGoodNodesInBinaryTree {

    private static int count = 0;

    public static void main(String[] args) {

        TreeNode root;

        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        assert 4 == solution(root);

        root = new TreeNode(3);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        assert 3 == solution(root);

        root = new TreeNode(1);
        assert 1 == solution(root);

    }

    private static int solution(TreeNode root) {
        count = 0;
        if (root == null)
            return count;
        dfs(root, root.val);
        return count;
    }

    private static void dfs(TreeNode node, int max) {
        if (node.val >= max)
            count++;
        int newMax = Math.max(max, node.val);
        if (node.left != null)
            dfs(node.left, newMax);
        if (node.right != null)
            dfs(node.right, newMax);
    }

}
