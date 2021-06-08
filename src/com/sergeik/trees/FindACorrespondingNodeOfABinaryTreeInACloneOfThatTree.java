package com.sergeik.trees;

import java.util.Stack;

/**
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 *
 * The cloned tree is a copy of the original tree.
 *
 * Return a reference to the same node in the cloned tree.
 *
 * Note that you are not allowed to change any of the two trees or the target node and the answer must
 * be a reference to a node in the cloned tree.
 *
 * Follow up: Solve the problem if repeated values on the tree are allowed.
 */
public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {

    public static void main(String[] args) {
        TreeNode oTarget = new TreeNode(5);
        TreeNode cTarget = new TreeNode(5);
        TreeNode root = buildTree(oTarget);
        TreeNode clone = buildTree(cTarget);

        assert cTarget.equals(getTargetCopy(root, clone, oTarget));

    }

    private static TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target)
            return cloned;
        TreeNode node = getTargetCopy(original.left, cloned.left, target);
        if (node != null)
            return node;
        return getTargetCopy(original.right, cloned.right, target);
    }

    private static TreeNode stackSolution(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Stack<TreeNode> stack = new Stack<>();
        if (cloned == null)
            return null;
        stack.push(cloned);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (target.val == node.val)
                return node;
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
        }
        return null;
    }

    private static TreeNode buildTree(TreeNode target) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = target;
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }

}
