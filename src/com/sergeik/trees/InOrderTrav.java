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
        assert ListHelper.compareIntLists(Arrays.asList(1,3,2), inorderTraversal(root));

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

    private static List<Integer> inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }

}
