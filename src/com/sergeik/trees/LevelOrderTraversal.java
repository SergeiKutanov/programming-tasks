package com.sergeik.trees;

import java.util.*;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        root.right = right;

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(3));
        expected.add(Arrays.asList(9, 20));
        expected.add(Arrays.asList(15, 7));

        List<List<Integer>> result = solution(root);
        assert expected.equals(result);

        root = null;
        expected = new LinkedList<>();
        result = solution(root);
        assert expected.equals(result);
    }

    private static List<List<Integer>> solution(TreeNode root) {
        Queue<TreeNode> levelNodes = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        if (root == null)
            return result;
        levelNodes.add(root);
        while (!levelNodes.isEmpty()) {
            List<TreeNode> nextLevelNodes = new LinkedList<>();
            result.add(processLevel(levelNodes, nextLevelNodes));
            if (!nextLevelNodes.isEmpty())
                levelNodes.addAll(nextLevelNodes);
        }
        return result;
    }

    private static List<Integer> processLevel(Queue<TreeNode> levelNodes, List<TreeNode> nextLevelNodes) {
        List<Integer> levelTraversal = new LinkedList<>();
        while (!levelNodes.isEmpty()) {
            TreeNode node = levelNodes.poll();
            levelTraversal.add(node.val);
            if (node.left != null)
                nextLevelNodes.add(node.left);
            if (node.right != null)
                nextLevelNodes.add(node.right);
        }
        return levelTraversal;
    }

}
