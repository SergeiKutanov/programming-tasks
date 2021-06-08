package com.sergeik.trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary
 * tree is said to be good if the length of the shortest path between them is less than or equal to distance.
 *
 * Return the number of good leaf node pairs in the tree.
 */
public class NumberOfGoodLeafNodesPairs {

    private static int count = 0;

    public static void main(String[] args) {
        TreeNode root;
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        assert 2 == solution(root, 3);
    }

    private static int solution(TreeNode root, int distance) {
        count = 0;
        dfs(root, distance);
        return count;
    }

    private static List<Integer> dfs(TreeNode node, int distance) {
        if (node == null)
            return new LinkedList<>();
        if (node.left == null && node.right == null) {
            return Arrays.asList(1);
        }
        List<Integer> leftSubTree = dfs(node.left, distance);
        List<Integer> rightSubTree = dfs(node.right, distance);

        for (int l: leftSubTree)
            for (int r: rightSubTree)
                if (l + r <= distance)
                    count++;

        List<Integer> res = new LinkedList<>();
        for (int l: leftSubTree)
            res.add(l + 1);
        for (int r: rightSubTree)
            res.add(r + 1);
        return res;
    }

}
