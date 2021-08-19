package com.sergeik.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the
 * product of the sums of the subtrees is maximized.
 *
 * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it
 * modulo 109 + 7.
 *
 * Note that you need to maximize the answer before taking the mod and not after taking it.
 */
public class MaximumProductOfSplittedBinaryTree {

    public static void main(String[] args) {
        TreeNode root;

        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        assert 90 == maxProduct(root);
    }

    static long res = 0, total = 0, sub;
    public static int maxProduct(TreeNode root) {
        total = s(root); s(root);
        return (int)(res % (int)(1e9 + 7));
    }

    private static long s(TreeNode root) {
        if (root == null) return 0;
        sub = root.val + s(root.left) + s(root.right);
        res = Math.max(res, sub * (total - sub));
        return sub;
    }

    private static int solution(TreeNode root) {
        Map<TreeNode, long[]> memo = new HashMap<>();
        res = Long.MIN_VALUE;
        long sum = getSum(root, memo);
        getMaxProduct(root, sum, memo);
        return (int) (res % 1000000007);
    }

    private static void getMaxProduct(TreeNode node, long sum, Map<TreeNode, long[]> memo) {
        if (node == null)
            return;
        long[] sums = memo.get(node);
        long left = sums[0], right = sums[1];
        res = Math.max(res, (sum - left) * left);
        res = Math.max(res, (sum - right) * right);
        getMaxProduct(node.left, sum, memo);
        getMaxProduct(node.right, sum, memo);
    }

    private static long getSum(TreeNode node, Map<TreeNode, long[]> memo) {
        if (node == null)
            return 0;
        long[] sums = new long[] {
                getSum(node.left, memo),
                getSum(node.right, memo)
        };
        memo.put(node, sums);
        return node.val + sums[0] + sums[1];
    }

}
