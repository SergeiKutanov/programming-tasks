package com.sergeik.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */
public class SameTree {

    public static void main(String[] args) {
        TreeNode p =  new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode q =  new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        assert isSameTree(p, q);

        TreeNode tmp = q.left;
        q.left = q.right;
        q.right = tmp;

        assert !isSameTree(p, q);
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    private static boolean itSolution(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while(!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            if (n1 == null ^ n2 == null)
                return false;
            if (n1 != null && n2 != null) {
                if (n1.val != n2.val)
                    return false;
                queue.offer(n1.left);
                queue.offer(n2.left);
                queue.offer(n1.right);
                queue.offer(n2.right);
            }
        }
        return true;
    }


}
