package com.sergeik.trees;

import java.util.*;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1)
 * and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index
 * starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the
 * same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 */
public class VerticalOrderTraversalOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root;
        List<List<Integer>> res, exp;

        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        res = solution(root);
        exp = new LinkedList<>();
        exp.add(Arrays.asList(0));
        exp.add(Arrays.asList(1));
        exp.add(Arrays.asList(3,2,2));
        exp.add(Arrays.asList(4));
        for (int i = 0; i < exp.size(); i++) {
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));
        }


        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        res = solution(root);
        exp = new LinkedList<>();
        exp.add(Arrays.asList(4));
        exp.add(Arrays.asList(2));
        exp.add(Arrays.asList(1,5,6));
        exp.add(Arrays.asList(3));
        exp.add(Arrays.asList(7));
        for (int i = 0; i < exp.size(); i++) {
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));
        }

        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        res = solution(root);
        exp = new LinkedList<>();
        exp.add(Arrays.asList(9));
        exp.add(Arrays.asList(3,15));
        exp.add(Arrays.asList(20));
        exp.add(Arrays.asList(7));
        for (int i = 0; i < exp.size(); i++) {
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));
        }
    }

    private static List<List<Integer>> solution(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        traverse(root, 0, 0, map);
        List<List<Integer>> res = new LinkedList<>();
        for (int x: map.keySet()) {
            List<Integer> level = new LinkedList<>();
            for (int y: map.get(x).keySet()) {
                PriorityQueue<Integer> nodes = map.get(x).get(y);
                while (!nodes.isEmpty())
                    level.add(nodes.poll());
            }
            res.add(level);
        }
        return res;
    }

    private static void traverse(TreeNode node, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (!map.containsKey(x))
            map.put(x, new TreeMap<>());
        if (!map.get(x).containsKey(y))
            map.get(x).put(y, new PriorityQueue<>());
        map.get(x).get(y).offer(node.val);
        if (node.left != null)
            traverse(node.left, x - 1, y + 1, map);
        if (node.right != null)
            traverse(node.right, x + 1, y + 1, map);
    }



}
