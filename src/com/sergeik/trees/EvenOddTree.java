package com.sergeik.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary tree is named Even-Odd if it meets the following conditions:
 *
 * The root of the binary tree is at level index 0, its children are at level index 1, their children are at
 * level index 2, etc.
 * For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order
 * (from left to right).
 * For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order
 * (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 */
public class EvenOddTree {

    public static void main(String[] args) {
        TreeNode root;
        root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(12);
        root.left.left.right = new TreeNode(8);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.right.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(2);
        assert solution(root);
    }

    private static boolean solution(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)
            return false;
        queue.offer(root);
        boolean even = true;
        TreeNode prev = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (even) {
                    if (cur.val % 2 == 0)
                        return false;
                    if (prev != null && prev.val >= cur.val)
                        return false;
                } else {
                    if (cur.val % 2 != 0)
                        return false;
                    if (prev != null && prev.val <= cur.val)
                        return false;
                }
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
                prev = cur;
            }
            prev = null;
            even = !even;
        }
        return true;
    }

}
