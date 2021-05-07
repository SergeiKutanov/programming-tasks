package com.sergeik.trees;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once. Note that the path
 * does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any path.
 */
public class BinaryTreeMaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Res res = new Res();
        maxPathSum(root, res);
        assert 42 == res.val;

        res = new Res();
        maxPathSum(new TreeNode(-3), res);
        assert -3 == res.val;
    }

    /**
     * For each node there can be four ways that the max path goes through the node:
     * 1. Node only
     * 2. Max path through Left Child + Node
     * 3. Max path through Right Child + Node
     * 4. Max path through Left Child + Node + Max path through Right Child
     * @param root
     * @param res
     * @return
     */
    private static int maxPathSum(TreeNode root, Res res) {
        if (root == null)
            return 0;

        int maxLeftSubtree = maxPathSum(root.left, res);
        int maxRightSubtree = maxPathSum(root.right, res);

        /**
         * Maximum sum of single path ending on root
         */
        int maxSinglePath = Math.max(
                Math.max(maxLeftSubtree, maxRightSubtree) + root.val,
                root.val
        );

        /**
         * max sum of path going through root
         */
        int maxConsolidatedPath = Math.max(
                maxSinglePath,
                maxLeftSubtree + maxRightSubtree + root.val
        );

        /**
         * Store max sum so far
         */
        res.val = Math.max(
                res.val,
                maxConsolidatedPath
        );

        return maxSinglePath;
    }

    static class Res {
        int val = Integer.MIN_VALUE;
    }

}
