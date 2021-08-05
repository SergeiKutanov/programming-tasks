package com.sergeik.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given the root node of a maximum tree: a tree where every node has a value greater than any other
 * value in its subtree.
 *
 * Just as in the previous problem, the given tree was constructed from an list A (root = Construct(A)) recursively
 * with the following Construct(A) routine:
 *
 * If A is empty, return null.
 * Otherwise, let A[i] be the largest element of A.  Create a root node with value A[i].
 * The left child of root will be Construct([A[0], A[1], ..., A[i-1]])
 * The right child of root will be Construct([A[i+1], A[i+2], ..., A[A.length - 1]])
 * Return root.
 * Note that we were not given A directly, only a root node root = Construct(A).
 *
 * Suppose B is a copy of A with the value val appended to it.  It is guaranteed that B has unique values.
 *
 * Return Construct(B).
 */
public class MaximumBinaryTreeII {

    public static void main(String[] args) {
        TreeNode root, res;
        root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        res = solution(root, 5);
    }

    private static TreeNode solution(TreeNode root, int val) {
        List<Integer> a = new ArrayList<>();
        buildA(root, a);
        a.add(val);
        TreeNode b = buildB(a, 0, a.size());
        return b;
    }

    private static TreeNode buildB(List<Integer> a, int start, int end) {
        if (start >= end)
            return null;
        int maxIdx = start;
        for (int i = start + 1; i < end; i++) {
            if (a.get(i) > a.get(maxIdx))
                maxIdx = i;
        }
        TreeNode node = new TreeNode(a.get(maxIdx));
        node.left = buildB(a, start, maxIdx);
        node.right = buildB(a, maxIdx + 1, end);
        return node;
    }

    private static void buildA(TreeNode node, List<Integer> a) {
        if (node == null)
            return;
        buildA(node.left, a);
        a.add(node.val);
        buildA(node.right, a);
    }

}
