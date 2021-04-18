package com.sergeik.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class ValidBST {

    /**
     * Used for in order traversal in place solution
     */
    private static Integer prev;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        TreeNode rightTree = new TreeNode(10);
        root.right = rightTree;
        rightTree.left = new TreeNode(7);
        rightTree.right = new TreeNode(12);

        assert inOrderTravSolution(root);
        assert inPlaceSolution(root);
        assert inOrderInPlaceTraversal(root);


        root = new TreeNode(5);
        root.left = new TreeNode(3);
        rightTree = new TreeNode(10);
        root.right = rightTree;
        rightTree.left = new TreeNode(7);
        rightTree.right = new TreeNode(9);
        assert !inOrderTravSolution(root);
        assert !inPlaceSolution(root);
        assert !inOrderInPlaceTraversal(root);

        root = new TreeNode(1);
        root.left = new TreeNode(1);
        assert !inOrderTravSolution(root);
        assert !inPlaceSolution(root);
        assert !inOrderInPlaceTraversal(root);

        root = new TreeNode(Integer.MIN_VALUE);
        assert inOrderTravSolution(root);
        assert inPlaceSolution(root);
        assert inOrderInPlaceTraversal(root);

        root = new TreeNode(5);
        root.left = new TreeNode(4);
        rightTree = new TreeNode(6);
        rightTree.left = new TreeNode(3);
        rightTree.right = new TreeNode(7);
        root.right = rightTree;
        assert !inOrderTravSolution(root);
        assert !inPlaceSolution(root);
        assert !inOrderInPlaceTraversal(root);

    }

    /**
     * Traverses inorder and keeps track of previous value
     * @param root
     * @return
     */
    private static boolean inOrderInPlaceTraversal(TreeNode root) {
        prev = null;
        return inOrderTravVisit(root);
    }

    private static boolean inOrderTravVisit(TreeNode node) {
        if (node == null)
            return true;
        if (!inOrderTravVisit(node.left))
            return false;
        if (prev != null && node.val <= prev) {
            return false;
        }

        prev = node.val;
        return inOrderTravVisit(node.right);
    };

    /**
     * Inorder traversal with array
     * @param root
     * @return
     */
    private static boolean inOrderTravSolution(TreeNode root) {
        List<Integer> tmpArr = new ArrayList<>();
        visitInorder(root, tmpArr);

        Integer min = null;
        for (int value :
                tmpArr) {
            if (min != null && min >= value)
                return false;
            min = value;
        }

        return true;
    }

    private static void visitInorder(TreeNode treeNode, List<Integer> arr) {
        if (treeNode == null)
            return;
        visitInorder(treeNode.left, arr);
        arr.add(treeNode.val);
        visitInorder(treeNode.right, arr);
    }

    /**
     * In place solution wth keeping min and max range
     * @param root
     * @return
     */
    private static boolean inPlaceSolution(TreeNode root) {
        return isBSTUtil(root, null, null);
    }

    private static boolean isBSTUtil(TreeNode node, Integer min, Integer max) {
        if (node == null)
            return true;

        if ((min != null && node.val < min) || (max != null && node.val > max))
            return false;

        return isBSTUtil(node.left, min, node.val - 1)
                && isBSTUtil(node.right, node.val + 1, max);
    }



}
