package com.sergeik.trees;

import java.util.HashMap;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
 * is the postorder traversal of the same tree, construct and return the binary tree.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static void main(String[] args) {
        TreeNode res = solution(
            new int[] {9,3,15,20,7},
            new int[] {9,15,7,20,3}
        );
    }

    private static TreeNode solution(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, map);
    }

    private static TreeNode buildTree(int[] inorder, int[] postorder, int is, int ie, int ps, int pe, HashMap<Integer, Integer> map) {
        if (is > ie || ps > pe)
            return null;
        TreeNode node = new TreeNode(postorder[pe]);
        int ri = map.get(postorder[pe]);
        node.left = buildTree(inorder, postorder, is, ri - 1, ps, ps + ri - is - 1, map);
        node.right = buildTree(inorder, postorder, ri + 1, ie, ps + ri - is, pe - 1, map);
        return node;
    }

}
