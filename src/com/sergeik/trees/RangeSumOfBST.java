package com.sergeik.trees;

import java.util.Stack;

/**
 * Given the root node of a binary search tree and two integers low and high, return the sum of values of
 * all nodes with a value in the inclusive range [low, high].
 */
public class RangeSumOfBST {

    private static Integer sum = 0;

    public static void main(String[] args) {
        TreeNode root;
        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(18);

        assert 32 == solution(root, 7, 15);
    }

    private static int solution(TreeNode root, int low, int high) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val >= low && node.val <= high)
                sum += node.val;
            if (node.left != null && node.val >= low)
                stack.push(node.left);
            if (node.right != null && node.val <= high)
                stack.push(node.right);
        }
        return sum;
    }

    private static int recSolution(TreeNode root, int low, int high) {
        traverse(root, low, high);
        return sum;
    }

    private static void traverse(TreeNode root, int low, int high) {
        if (root.val >= low && root.val <= high)
            sum += root.val;
        if (root.left != null && root.val >= low)
            traverse(root.left, low, high);
        if (root.right != null && root.val <= high)
            traverse(root.right, low, high);
    }

}
