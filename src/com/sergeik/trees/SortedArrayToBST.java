package com.sergeik.trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SortedArrayToBST {

    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        TreeNode rootResult = recSolution(nums);
        List<Integer> resultArr = new LinkedList<>();
        preOrder(rootResult, resultArr);
        assert resultArr.equals(Arrays.asList(0, -10, -3, 5, 9));
    }

    private static void preOrder(TreeNode node, List<Integer> arr) {
        if (node == null)
            return;
        arr.add(node.val);
        preOrder(node.left, arr);
        preOrder(node.right, arr);
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
