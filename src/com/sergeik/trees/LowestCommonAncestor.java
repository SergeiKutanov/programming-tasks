package com.sergeik.trees;

import java.util.*;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);

        TreeNode right = new TreeNode(1);
        right.left = new TreeNode(0);
        right.right = new TreeNode(8);
        root.right = right;

        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        assert root.left.equals(solutionTwoPaths(root, root.left, root.left.right.right));
        assert root.left.equals(solution(root, root.left, root.left.right.right));

    }

    private static TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = solution(root.left, p, q);
        TreeNode right = solution(root.right, p, q);

        if (left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    private static TreeNode solutionTwoPaths(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = root;

        List<TreeNode> pathToP = new ArrayList<>();
        findPath(root, p, pathToP);

        List<TreeNode> pathToQ = new ArrayList<>();
        findPath(root, q, pathToQ);

        int i = 0;
        while (i < pathToP.size() && i < pathToQ.size()) {
            if (pathToP.get(i).equals(pathToQ.get(i))) {
                lca = pathToP.get(i);
                i++;
            } else {
                return lca;
            }
        }

        return lca;
    }

    private static boolean findPath(TreeNode root, TreeNode node, List<TreeNode> path) {
        if (root == null)
            return false;
        path.add(root);

        if (root.equals(node))
            return true;

        if (findPath(root.left, node, path))
            return true;
        if (findPath(root.right, node, path))
            return true;
        path.remove(root);
        return false;
    }

}
