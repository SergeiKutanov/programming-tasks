package com.sergeik.trees;

/**
 * Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * Recall that:
 *
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
 * The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S
 * is in the subtree with root A.
 */
public class LowestCommonAncestorOfDeepestLeaves {

    public static void main(String[] args) {
        TreeNode root, exp, res;

        root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        exp = root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        res = solution(root);
        assert exp.equals(res);
    }

    private static TreeNode res;
    private static int deepest;

    private static TreeNode solution(TreeNode root) {
        res = null;
        deepest = 0;
        dfs(root, 1);
        return res;
    }

    private static int dfs(TreeNode node, int level) {
        if (node == null)
            return level - 1;
        int leftDeepest = dfs(node.left, level + 1);
        int rightDeepest = dfs(node.right, level + 1);
        deepest = Math.max(leftDeepest, deepest);
        deepest = Math.max(rightDeepest, deepest);
        if (deepest == leftDeepest && deepest == rightDeepest)
            res = node;
        return Math.max(leftDeepest, rightDeepest);
    }

}
