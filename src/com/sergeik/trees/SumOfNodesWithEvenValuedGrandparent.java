package com.sergeik.trees;

/**
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.
 * (A grandparent of a node is the parent of its parent, if it exists.)
 *
 * If there are no nodes with an even-valued grandparent, return 0.
 */
public class SumOfNodesWithEvenValuedGrandparent {

    private static int sum = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(1);
        root.left.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(9);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(5);

        assert 18 == solution(root);

    }

    private static int solution(TreeNode root) {
        sum = 0;
        dfs(root, new TreeNode[2]);
        return sum;
    }

    private static void dfs(TreeNode node, TreeNode[] history) {
        if (history[0] != null && history[0].val % 2 == 0) {
            sum += node.val;
        }
        if (node.left != null)
            dfs(node.left, new TreeNode[] {history[1], node});
        if (node.right != null)
            dfs(node.right, new TreeNode[] {history[1], node});
    }

}
