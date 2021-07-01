package com.sergeik.trees;

import java.util.*;

/**
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 */
public class FindLeafsOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root;
        List<List<Integer>> exp = new LinkedList<>(), res;

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        exp.add(Arrays.asList(root.left.left.val, root.left.right.val, root.right.val));
        exp.add(Arrays.asList(root.left.val));
        exp.add(Arrays.asList(root.val));

        res = solution(root);
        for (int i = 0; i < exp.size(); i++)
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));

    }

    private static List<List<Integer>> solution(TreeNode root) {
        Set<TreeNode> seen = new HashSet<>();
        List<List<Integer>> res = new LinkedList<>();
        while (!seen.contains(root)) {
            List<Integer> level = new LinkedList<>();
            dfs(root, seen, level);
            res.add(level);
        }
        return res;
    }

    private static void dfs(TreeNode root, Set<TreeNode> seen, List<Integer> res) {
        if (root == null)
            return;
        if ((root.left == null || seen.contains(root.left)) && (root.right == null || seen.contains(root.right)) && !seen.contains(root)) {
            res.add(root.val);
            seen.add(root);
        } else {
            dfs(root.left, seen, res);
            dfs(root.right, seen, res);
        }
    }

}
