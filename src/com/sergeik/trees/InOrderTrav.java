package com.sergeik.trees;

import com.sergeik.linkedlists.ListHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 */
public class InOrderTrav {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        assert ListHelper.compareIntLists(Arrays.asList(1,3,2), solution(root));

    }

    private static List<Integer> solution(TreeNode root) {
        List<Integer> data = new ArrayList<>();
        inOrderTrav(root, data);
        return data;
    }

    private static void inOrderTrav(TreeNode node, List<Integer> data) {
        if (node == null)
            return;
        inOrderTrav(node.left, data);
        data.add(node.val);
        inOrderTrav(node.right, data);
    }

}
