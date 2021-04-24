package com.sergeik.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode leftSubTree = new TreeNode(2);
        leftSubTree.left = new TreeNode(3);
        leftSubTree.right = new TreeNode(4);
        root.left = leftSubTree;
        TreeNode rightSubTree = new TreeNode(2);
        rightSubTree.left = new TreeNode(4);
        rightSubTree.right = new TreeNode(3);
        root.right = rightSubTree;

        assert solution(root);
        assert itSolution(root);

        root = new TreeNode(1);
        leftSubTree = new TreeNode(2);
        leftSubTree.right = new TreeNode(3);
        root.left = leftSubTree;
        rightSubTree = new TreeNode(2);
        rightSubTree.right = new TreeNode(3);

        assert !solution(root);
        assert !itSolution(root);

    }

    private static boolean itSolution(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode root1 = queue.poll();
            TreeNode root2 = queue.poll();
            if (root1 == null && root2 == null)
                continue;
            if (root1 == null || root2 == null)
                return false;
            if (root1.val != root2.val)
                return false;
            queue.add(root1.left);
            queue.add(root2.right);
            queue.add(root1.right);
            queue.add(root2.left);
        }
        return true;
    }

    private static boolean solution(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;

        return (root1.val == root2.val)
                && isMirror(root1.left, root2.right)
                && isMirror(root1.right, root2.left);
    }

}
