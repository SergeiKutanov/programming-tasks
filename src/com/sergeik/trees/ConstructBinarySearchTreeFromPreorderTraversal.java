package com.sergeik.trees;

import java.util.Arrays;

/**
 * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree),
 * construct the tree and return its root.
 *
 * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the
 * given test cases.
 *
 * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less
 * than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
 *
 * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left,
 * then traverses Node.right.
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {

    public static void main(String[] args) {
        TreeNode root = solution(new int[] {8,5,1,7,10,12});
        Object[] res = new Object[7];
        TreeHelper.toArray(root, 0, res);
        Object[] exp = new Object[] {8,5,10,1,7,null,12};
        assert Arrays.equals(res, exp);
    }

    private static TreeNode solution(int[] preorder) {
        return build(preorder, 0, preorder.length - 1);
    }

    private static TreeNode build(int[] data, int start, int end) {
        if (start > end)
            return null;
        TreeNode node = new TreeNode(data[start]);
        int newEnd = start;
        while (newEnd < data.length && data[newEnd] <= data[start])
            newEnd++;
        node.left = build(data, start + 1, newEnd - 1);
        node.right = build(data, newEnd, end);
        return node;
    }

}
