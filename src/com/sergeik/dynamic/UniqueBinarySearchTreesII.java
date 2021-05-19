package com.sergeik.dynamic;

import com.sergeik.trees.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes 
 * of unique values from 1 to n. Return the answer in any order.
 */
public class UniqueBinarySearchTreesII {

    public static void main(String[] args) {
        List<TreeNode> res = solution(3);
    }

    private static List<TreeNode> solution(int n) {
        return getTrees(1, n);
    }

    private static List<TreeNode> getTrees(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = getTrees(start, i - 1);
            List<TreeNode> rightTrees = getTrees(i + 1, end);
            for (TreeNode leftTree: leftTrees) {
                for (TreeNode rightTree: rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    res.add(root);
                }
            }
        }
        return res;
    }

}
