package com.sergeik.trees;

import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.
 *
 * A leaf is a node with no children.
 */
public class PathSumII {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        solution(root, 1);

        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        solution(root, 22);

    }

    private static List<List<Integer>> solution(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null)
            return res;
        preOrder(root, targetSum, res, new LinkedList<>());
        return res;
    }

    private static boolean preOrder(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> tmp) {
        int newTarget = targetSum - root.val;
        tmp.add(root.val);
        boolean inLeft = false;
        boolean inRight = false;
        if (root.left != null) {
            inLeft = preOrder(root.left, newTarget, res, tmp);
        }
        if (root.right != null) {
            inRight = preOrder(root.right, newTarget, res, tmp);
        }
        if (newTarget == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(tmp));
        }
        if (!inLeft && !inRight)
            tmp.remove(tmp.size() - 1);
        return inLeft || inRight;
    }

}
