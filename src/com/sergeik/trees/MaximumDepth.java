package com.sergeik.trees;

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 */
public class MaximumDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode rightSubtree = new TreeNode(20);
        rightSubtree.left = new TreeNode(15);
        rightSubtree.right = new TreeNode(7);
        root.right = rightSubtree;

        assert 3 == solution(root);

    }

    private static int solution(TreeNode root) {
        return maxDepth(root);
    }

    /**
     * If tree is empty then return 0
     * 2. Else
     *      (a) Get the max depth of left subtree recursively  i.e.,
     *           call maxDepth( tree->left-subtree)
     *      (a) Get the max depth of right subtree recursively  i.e.,
     *           call maxDepth( tree->right-subtree)
     *      (c) Get the max of max depths of left and right
     *           subtrees and add 1 to it for the current node.
     *          max_depth = max(max dept of left subtree,
     *                              max depth of right subtree)
     *                              + 1
     *      (d) Return max_depth
     * @param node
     * @return
     */
    private static int maxDepth(TreeNode node) {
        if (node == null)
            return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        if (leftDepth > rightDepth)
            return leftDepth + 1;
        return rightDepth + 1;
    }

}
