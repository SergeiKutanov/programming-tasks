package com.sergeik.trees;

public class SortedArrayToBST {

    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        TreeNode rootResult = recSolution(nums);

    }

    private static TreeNode recSolution(int[] nums) {
        return getMiddleNode(0, nums.length - 1, nums);
    }

    private static TreeNode getMiddleNode(int start, int end, int[] nums) {

        if (start > end)
            return null;

        int middle = (start + end) / 2;
        TreeNode node = new TreeNode(nums[middle]);
        node.left = getMiddleNode(start, middle - 1, nums);
        node.right = getMiddleNode(middle + 1, end, nums);
        return node;
    }
}
