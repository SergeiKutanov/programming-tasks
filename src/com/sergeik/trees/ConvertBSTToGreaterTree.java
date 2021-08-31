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
 */
public class ConvertBSTToGreaterTree {

    public static void main(String[] args) {
        TreeNode root, res;
        root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
        res = solution(root);
        String foo = "foo";
    }

    private static TreeNode solution(TreeNode root) {
        postOrder(root, 0);
        return root;
    }

    private static int postOrder(TreeNode node, int cur) {
        if (node == null)
            return cur;
        cur = postOrder(node.right, cur);
        node.val += cur;
        cur = postOrder(node.left, node.val);
        return cur;
    }

}
