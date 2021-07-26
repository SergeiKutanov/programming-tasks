package com.sergeik.trees;

/**
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 *
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the
 * value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of
 * the root node is 0.
 *
 * If a node has only one child, that child is guaranteed to be the left child.
 *
 * Given the output traversal of this traversal, recover the tree and return its root.
 */
public class RecoverATreeFromPreorderTraversal {

    public static void main(String[] args) {
        TreeNode res = solution("1-2--3--4-5--6--7");
    }

    private static TreeNode solution(String traversal) {
        return getNode(0, new StringBuilder(traversal));
    }

    private static TreeNode getNode(int level, StringBuilder traversal) {
        if (traversal.length() == 0)
            return null;
        int dashes = 0;
        while (dashes < traversal.length() && traversal.charAt(dashes) == '-')
            dashes++;
        if (dashes == level) {
            int digit = 0, digitStart = dashes;
            while (digitStart < traversal.length() && traversal.charAt(digitStart) != '-') {
                digit *= 10;
                digit += traversal.charAt(digitStart) - '0';
                digitStart++;
            }
            traversal.delete(0, digitStart);
            TreeNode node = new TreeNode(digit);
            node.left = getNode(level + 1, traversal);
            node.right = getNode(level + 1, traversal);
            return node;
        }
        return null;
    }

}
