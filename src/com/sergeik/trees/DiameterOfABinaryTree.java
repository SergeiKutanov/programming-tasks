package com.sergeik.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfABinaryTree {

    private static int max = 0;

    public static void main(String[] args) {
        TreeNode root;

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        assert 3 == diameterOfBinaryTree(root);

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        assert 1 == diameterOfBinaryTree(root);
    }

    private static int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        maxDepth(root);
        return max;
    }

    private static int maxDepth(TreeNode node) {
        if (node == null)
            return 0;
        int left = maxDepth(node.left), right = maxDepth(node.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

    private static int dfsSolution(TreeNode root) {
        return getMaxRadius(root, new HashMap<>());
    }

    private static int getMaxRadius(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null)
            return 0;
        int leftHeight = getHeight(root.left, memo);
        int rightHeight = getHeight(root.right, memo);
        int radius = leftHeight + rightHeight;
        int leftRadius = getMaxRadius(root.left, memo);
        int rightRadius = getMaxRadius(root.right, memo);
        radius = Math.max(radius, Math.max(leftRadius, rightRadius));
        return radius;
    }

    private static int getHeight(TreeNode node, Map<TreeNode, Integer> memo) {
        if (node == null)
            return 0;
        if (memo.containsKey(node))
            return memo.get(node);
        int h = Math.max(getHeight(node.left, memo) + 1, getHeight(node.right, memo) + 1);
        memo.put(node, h);
        return h;
    }

}
