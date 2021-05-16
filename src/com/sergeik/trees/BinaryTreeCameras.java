package com.sergeik.trees;

/**
 * Given a binary tree, we install cameras on the nodes of the tree.
 *
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 */
public class BinaryTreeCameras {

    private static int res = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.right = new TreeNode(0);

        assert 2 == solution(root);

    }

    private static int solution(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    /**
     * Apply a recusion function dfs.
     * Return 0 if it's a leaf.
     * Return 1 if it's a parent of a leaf, with a camera on this node.
     * Return 2 if it's coverd, without a camera on this node.
     *
     * For each node,
     * if it has a child, which is leaf (node 0), then it needs camera.
     * if it has a child, which is the parent of a leaf (node 1), then it's covered.
     *
     * If it needs camera, then res++ and we return 1.
     * If it's covered, we return 2.
     * Otherwise, we return 0.
     * @param node
     * @return
     */
    private static int dfs(TreeNode node) {
        if (node == null)
            return 2;
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return (left == 1 || right == 1) ? 2 : 0;
    }

}
