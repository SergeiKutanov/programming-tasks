package com.sergeik.dynamic;

import com.sergeik.trees.TreeNode;

import java.util.*;

/**
 * You are given the root of a binary tree.
 *
 * A ZigZag path for a binary tree is defined as follow:
 *
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 */
public class LongestZigZag {

    public static void main(String[] args) {
        TreeNode root;
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(6);
        root.right.right.left.right = new TreeNode(7);
        root.right.right.left.right.right = new TreeNode(8);
        assert 3 == solution(root);
    }

    /**
     * Recursive return [left, right, result], where:
     * left is the maximum length in direction of root.left
     * right is the maximum length in direction of root.right
     * result is the maximum length in the whole sub tree.
     * @param root
     * @return
     */
    private static int solution(TreeNode root) {
        return qDfs(root)[2];
    }

    private static int[] qDfs(TreeNode node) {
        if (node == null)
            //leftMax, rightMax, res
            return new int[] {-1,-1,-1};
        int[] left = qDfs(node.left);
        int[] right = qDfs(node.right);
        int res = Math.max(
                Math.max(left[1], right[0]) + 1,
                Math.max(left[2], right[2])
        );
        return new int[] {left[1] + 1, right[0] + 1, res};
    }

    private static int dfsFullSolution(TreeNode root) {
        Queue<TreeNode> bfs = new LinkedList<>();
        Map<TreeNode, int[]> memo = new HashMap<>();
        if (root == null)
            return 0;
        bfs.offer(root);
        int max = Integer.MIN_VALUE;
        while (!bfs.isEmpty()) {
            TreeNode node = bfs.poll();
            int leftDfs = dfs(node, 0, memo);
            int rightDfs = dfs(node, 1, memo);
            max = Math.max(max , Math.max(leftDfs, rightDfs));
            if (node.left != null)
                bfs.offer(node.left);
            if (node.right != null)
                bfs.offer(node.right);
        }
        return max;
    }

    private static int dfs(TreeNode node, int dir, Map<TreeNode, int[]> memo) {
        if (node == null)
            return 0;
        if (memo.containsKey(node) && memo.get(node)[dir] >= 0) {
            return memo.get(node)[dir];
        }
        memo.computeIfAbsent(node, l -> new int[] {-1,-1});
        if (dir == 0) {
            //left
            int len = node.left != null ? dfs(node.left, 1, memo) + 1 : 0;
            memo.get(node)[0] = len;
            return len;
        }
        int len = node.right != null ? dfs(node.right, 0, memo) + 1 : 0;
        memo.get(node)[1] = len;
        return len;
    }

}
