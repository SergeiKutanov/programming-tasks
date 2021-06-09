package com.sergeik.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val
 * at the given depth depth.
 *
 * Note that the root node is at depth 1.
 *
 * The adding rule is:
 *
 * Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with
 * value val as cur's left subtree root and right subtree root.
 * cur's original left subtree should be the left subtree of the new left subtree root.
 * cur's original right subtree should be the right subtree of the new right subtree root.
 * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the
 * new root of the whole original tree, and the original tree is the new root's left subtree.
 */
public class AddOneRowToTree {

    public static void main(String[] args) {
        TreeNode root;
        TreeNode res;
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        res = solution(root, 1, 1);

        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        res = solution(root, 1, 2);
        int v = 0;
    }

    private static TreeNode solution(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node  = new TreeNode(val);
            node.left = root;
            return node;
        }
        traverse(root, 1, val, depth);
        return root;
    }

    private static void traverse(TreeNode node, int level, int val, int depth) {
        if (level == depth - 1) {
            TreeNode left = new TreeNode(val);
            TreeNode right = new TreeNode(val);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
            return;
        }
        if (node.left != null)
            traverse(node.left, level + 1, val, depth);
        if (node.right != null)
            traverse(node.right, level + 1, val, depth);
    }

    private static TreeNode queueSolution(TreeNode root, int val, int depth) {
        Queue<TreeNode> bfs = new LinkedList<>();
        int cDepth = 1;
        if (root == null)
            return new TreeNode(val);

        if (depth == cDepth) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        bfs.offer(root);
        while (!bfs.isEmpty()) {
            cDepth++;
            if (depth == cDepth) {
                break;
            }
            int size = bfs.size();
            while (size-- > 0) {
                TreeNode node = bfs.poll();
                if (node.left != null)
                    bfs.offer(node.left);
                if (node.right != null)
                    bfs.offer(node.right);
            }
        }

        while (!bfs.isEmpty()) {
            TreeNode node = bfs.poll();
            TreeNode nodeLeft = node.left;
            TreeNode nodeRight = node.right;
            node.left = new TreeNode(val);
            node.right = new TreeNode(val);
            node.left.left = nodeLeft;
            node.right.right = nodeRight;
        }

        return root;
    }

}
