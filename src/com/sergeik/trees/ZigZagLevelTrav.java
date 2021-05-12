package com.sergeik.trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. 
 * (i.e., from left to right, then right to left for the next level and alternate between).
 *
 */
public class ZigZagLevelTrav {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> expected = new LinkedList<>();
        expected.add(Arrays.asList(3));
        expected.add(Arrays.asList(20, 9));
        expected.add(Arrays.asList(15, 7));

        List<List<Integer>> res = solution(root);
        assert validate(expected, res);
    }

    private static List<List<Integer>> solution(TreeNode root) {
        boolean odd = true;
        List<List<Integer>> res = new LinkedList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<Integer> layer = new LinkedList<>();
            while (queueSize > 0) {
                queueSize--;
                TreeNode node = queue.poll();
                if (odd) {
                    layer.add(node.val);
                } else {
                    layer.add(0, node.val);
                }
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            res.add(layer);
            odd = !odd;
        }
        return res;
    }

    private static boolean validate(List<List<Integer>> l1, List<List<Integer>> l2) {
        if (l1.size() != l2.size())
            return false;

        for (int i = 0; i < l1.size(); i++) {
            List<Integer> ll1 = l1.get(i);
            List<Integer> ll2 = l2.get(i);
            if (ll1.size() != ll2.size())
                return false;
            if (!Arrays.equals(ll1.toArray(), ll2.toArray()))
                return false;
        }
        return true;
    }

}
