package com.sergeik.trees;

import java.util.Arrays;

/**
 * Given a binary tree root and an integer target, delete all the leaf nodes with value target.
 *
 * Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the
 * value target, it should also be deleted (you need to continue doing that until you can't).
 */
public class DeleteLeavesWithAGivenValue {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        TreeNode res = removeLeafNodes(root, 2);

        Object[] obj = new Object[7];
        TreeHelper.toArray(res, 0, obj);
        assert Arrays.equals(new Object[] {1, null, 3, null, null, null, 6}, obj);
    }

    private static TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root.left != null) root.left = removeLeafNodes(root.left, target);
        if (root.right != null) root.right = removeLeafNodes(root.right, target);
        //root.left == root.right == null - leaf node
        return root.left == root.right && root.val == target ? null : root;
    }

}
