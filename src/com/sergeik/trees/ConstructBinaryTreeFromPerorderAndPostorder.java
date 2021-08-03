package com.sergeik.trees;

import java.util.Arrays;

/**
 * Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of
 * distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
 *
 * If there exist multiple answers, you can return any of them.
 *
 *
 */
public class ConstructBinaryTreeFromPerorderAndPostorder {

    private static int[] map;

    public static void main(String[] args) {
        TreeNode root = constructFromPrePost(new int[] {1,2,4,5,3,6,7}, new int[] {4,5,2,6,7,3,1});
        Object[] res = new Object[7];
        TreeHelper.toArray(root, 0, res);
        assert Arrays.equals(res, new Object[] {1,2,3,4,5,6,7});
    }

    static int preIndex = 0, posIndex = 0;
    public static TreeNode constructFromPrePost(int[]pre, int[]post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (root.val != post[posIndex])
            root.left = constructFromPrePost(pre, post);
        if (root.val != post[posIndex])
            root.right = constructFromPrePost(pre, post);
        posIndex++;
        return root;
    }

    private static TreeNode solution(int[] preorder, int[] postorder) {
        int length = preorder.length;
        map = new int[31];
        for (int i = 0; i < postorder.length; i++) {
            map[postorder[i]] = i;
        }
        return build(0, length - 1, 0, length - 1, preorder, postorder);
    }

    private static TreeNode build(int preLeft, int preRight, int postLeft, int postRight, int[] pre, int[] post) {
        if (preLeft > preRight || postLeft > postRight)
            return null;
        TreeNode node = new TreeNode(pre[preLeft]);
        if (preLeft + 1 <= preRight) {
            int index = map[pre[preLeft + 1]];
            int sum = index - postLeft;
            node.left = build(preLeft + 1, preLeft + sum + 1, postLeft, postLeft + sum, pre, post);
            node.right = build(preLeft + sum + 2, preRight, postLeft + sum + 1, postRight - 1, pre, post);
        }
        return node;
    }

}
