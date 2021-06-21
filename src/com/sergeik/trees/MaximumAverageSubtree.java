package com.sergeik.trees;

/**
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 *
 * (A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the
 * sum of its values, divided by the number of nodes.)
 */
public class MaximumAverageSubtree {

    private static double ans = 0;

    public static void main(String[] args) {
        TreeNode root;

        root = new TreeNode(2);
        root.left = new TreeNode(6);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(12);
        root.right.left.left = new TreeNode(0);
        root.right.left.right = new TreeNode(4);
        root.right.left.right.left = new TreeNode(11);
        root.right.left.right.left.right = new TreeNode(1);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(10);
        root.right.right.right.right = new TreeNode(9);
        root.right.right.right.right.right = new TreeNode(8);
        assert 9 == solution(root);

        root = new TreeNode(2);
        root.right = new TreeNode(1);
        assert 1.5 == solution(root);

        root = new TreeNode(5);
        root.left = new TreeNode(6);
        root.right = new TreeNode(1);
        assert 6.0 == solution(root);
    }

    private static double solution(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private static int[] dfs(TreeNode root) {
        if (root == null)
            return new int[]{0,0};
        int[] leftNodes = dfs(root.left);
        int[] rightNodes = dfs(root.right);
        int[] res = new int[] {root.val + leftNodes[0] + rightNodes[0], leftNodes[1] + rightNodes[1] + 1};
        double avg = res[0] / (double) res[1];
        ans = Math.max(ans, avg);
        return res;
    }

}
