package com.sergeik.trees;

import java.util.Arrays;

/**
 * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from
 * nums using the following algorithm:
 *
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 */
public class MaximumBinaryTree {

    public static void main(String[] args) {
        TreeNode root;
        Object[] exp, res;

        root = solution(new int[] {3,2,1,6,0,5});
        exp = new Object[]{6,3,5,null,2,0,null,null,null,null,1};
        res = new Object[11];
        TreeHelper.toArray(root, 0, res);
        assert Arrays.equals(exp, res);
    }
    
    private static TreeNode solution(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private static TreeNode buildTree(int[] nums, int left, int right) {
        if (right == nums.length)
            return null;
        if (left < 0)
            return null;
        if (left > right)
            return null;

        int idx = findMax(nums, left, right);
        TreeNode node = new TreeNode(nums[idx]);
        node.left = buildTree(nums, left, idx - 1);
        node.right = buildTree(nums, idx + 1, right);
        return node;
    }

    private static int findMax(int[] nums, int left, int right) {
        int max = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[max] < nums[i])
                max = i;
        }
        return max;
    }

}
