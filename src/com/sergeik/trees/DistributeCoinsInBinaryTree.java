package com.sergeik.trees;

/**
 * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins.
 * There are n coins in total throughout the whole tree.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from
 * parent to child, or from child to parent.
 *
 * Return the minimum number of moves required to make every node have exactly one coin.
 */
public class DistributeCoinsInBinaryTree {

    private static int steps = 0;

    public static void main(String[] args) {
        TreeNode root;

        root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        assert 2 == solution(root);
    }

    private static int solution(TreeNode root) {
        steps = 0;
        dfs(root);
        return steps;
    }

    private static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        steps += Math.abs(left) + Math.abs(right);
        return node.val + left + right + 1;
    }

}
