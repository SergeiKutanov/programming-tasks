package com.sergeik.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root;
        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        solution(root);
    }

    private static void solution(TreeNode root) {
        List<Integer> data = new ArrayList<>();
        dfs(root, data);
        Collections.sort(data);
        restore(root, data);
        return;
    }

    private static void restore(TreeNode node, List<Integer> data) {
        if (node == null)
            return;
        restore(node.left, data);
        node.val = data.get(0);
        data.remove(0);
        restore(node.right, data);
    }

    private static void dfs(TreeNode node, List<Integer> data) {
        if (node == null)
            return;
        dfs(node.left, data);
        data.add(node.val);
        dfs(node.right, data);
    }

}
