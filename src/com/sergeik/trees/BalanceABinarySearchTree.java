package com.sergeik.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, return a balanced binary search tree with the same node values.
 *
 * A binary search tree is balanced if and only if the depth of the two subtrees of every node never
 * differ by more than 1.
 *
 * If there is more than one answer, return any of them.
 */
public class BalanceABinarySearchTree {

    public static void main(String[] args) {
        TreeNode root, res;
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        res = solution(root);
    }

    private static TreeNode solution(TreeNode root) {
        List<Integer> data = new ArrayList<>();
        traverse(root, data);
        return buildTree(data, 0, data.size());
    }

    private static TreeNode buildTree(List<Integer> data, int start, int end) {
        if (start >= end)
            return null;
        int m = (start + end) / 2;
        TreeNode node = new TreeNode(data.get(m));
        node.left = buildTree(data, start, m);
        node.right = buildTree(data, m + 1, end);
        return node;
    }

    private static void traverse(TreeNode node, List<Integer> data) {
        if (node == null)
            return;
        traverse(node.left, data);
        data.add(node.val);
        traverse(node.right, data);
    }

}
