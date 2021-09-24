package com.sergeik.trees;

import java.util.*;

/**
 * Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values
 * with the highest frequency in any order.
 *
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node
 * (including the node itself).
 */
public class MostFrequentSubtreeSum {

    public static void main(String[] args) {
        TreeNode root;
        root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
        assert Arrays.equals(new int[] {2, -3, 4}, solution(root));

        root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-5);
        assert Arrays.equals(new int[] {2}, solution(root));
    }

    private static int[] solution(TreeNode root) {
        Map<Integer, Integer> freq = new HashMap<>();
        dfs(root, freq);
        List<Integer> res = new ArrayList<>();
        int curMax = 0;
        for (int key: freq.keySet()) {
            int fr = freq.get(key);
            if (fr > curMax) {
                curMax = fr;
                res.clear();
                res.add(key);
            } else if (fr == curMax) {
                res.add(key);
            }
        }
        return res.stream().mapToInt(i->i).toArray();
    }

    private static int dfs(TreeNode node, Map<Integer, Integer> fr) {
        if (node == null)
            return 0;
        int left = dfs(node.left, fr);
        int right = dfs(node.right, fr);
        int sum = node.val + left + right;
        fr.put(sum, fr.getOrDefault(sum, 0) + 1);
        return sum;
    }

}
