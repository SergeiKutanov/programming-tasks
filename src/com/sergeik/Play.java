package com.sergeik;


import com.sergeik.trees.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Play {

    private static Map<TreeNode, Map<TreeNode, Boolean>> memo = new HashMap<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        assert root.equals(lowestCommonAncestor(root, root.left, root.right));
        assert root.left.equals(lowestCommonAncestor(root, root.left, root.left.right.right));
    }

    private static TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> pathToP = new LinkedList<>(), pathToQ = new LinkedList<>();
        buildPath(root, p, pathToP);
        buildPath(root, q, pathToQ);
        TreeNode res = null;
        while (!pathToP.isEmpty() && !pathToQ.isEmpty() && pathToP.peek().equals(pathToQ.peek())) {
            res = pathToP.poll();
            pathToQ.poll();
        }
        return res;
    }

    private static void buildPath(TreeNode root, TreeNode node, Queue<TreeNode> path) {
        if (contains(root, node))
            path.add(root);
        else
            return;
        buildPath(root.left, node, path);
        buildPath(root.right, node, path);
    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (contains(root.left, p) && contains(root.left, q))
            return lowestCommonAncestor(root.left, p, q);
        if (contains(root.right, p) && contains(root.right, q))
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    private static boolean contains(TreeNode node, TreeNode p) {
        if (node == null)
            return false;
        if (node.equals(p))
            return true;

        if (memo.containsKey(node) && memo.get(node).containsKey(p))
            return memo.get(node).get(p);

        memo.computeIfAbsent(node, l -> new HashMap<>());
        boolean inLeft = contains(node.left, p);
        boolean inRight = contains(node.right, p);
        memo.get(node).put(p, inLeft || inRight);
        return memo.get(node).get(p);
    }

}
