package com.sergeik.trees;

import java.util.*;

/**
 * Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in
 * the answer must have Node.val == 0.
 *
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees
 * in any order.
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 */
public class AllPossibleFullBinaryTrees {

    public static void main(String[] args) {
        List<TreeNode> res = allPossibleBFT(7);
    }

    private static List<TreeNode> allPossibleBFT(int n) {
        if (n == 0 || n % 2 == 0)
            return new ArrayList<>();
        List<TreeNode> res = new LinkedList<>();
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> left = allPossibleBFT(i);
            List<TreeNode> right = allPossibleBFT(n - i - 1);
            for (TreeNode l: left)
                for (TreeNode r: right) {
                    TreeNode root = new TreeNode(0);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
        }
        return res;
    }
}
