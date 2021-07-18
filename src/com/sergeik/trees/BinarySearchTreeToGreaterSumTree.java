package com.sergeik.trees;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original
 * BST is changed to the original key plus sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Note: This question is the same as 538: https://leetcode.com/problems/convert-bst-to-greater-tree/
 */
public class BinarySearchTreeToGreaterSumTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
        solution(root);
    }

    private static TreeNode solution(TreeNode root) {
        int[] count = new int[101];
        if (root == null)
            return root;
        trav(root, count);
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i - 1] + (count[i] == 0 ? 0 : i);
        }
        buildTree(root, count);
        return root;
    }

    private static void buildTree(TreeNode node, int[] count) {
        node.val += count[100] - count[node.val];
        if (node.left != null)
            buildTree(node.left, count);
        if (node.right != null)
            buildTree(node.right, count);
    }

    private static void trav(TreeNode root, int[] count) {
        count[root.val]++;
        if (root.left != null)
            trav(root.left, count);
        if (root.right != null)
            trav(root.right, count);
    }

}
