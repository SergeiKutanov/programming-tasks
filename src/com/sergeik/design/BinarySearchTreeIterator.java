package com.sergeik.design;

import com.sergeik.trees.TreeNode;

import java.util.Stack;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search
 * tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as
 * part of the constructor. The pointer should be initialized to a non-existent number smaller than any element
 * in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise
 * returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return
 * the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the
 * in-order traversal when next() is called.
 */
public class BinarySearchTreeIterator {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSTIterator bSTIterator = new BSTIterator(root);
        assert 3 == bSTIterator.next();    // return 3
        assert 7 == bSTIterator.next();    // return 7
        assert bSTIterator.hasNext(); // return True
        assert 9 == bSTIterator.next();    // return 9
        assert bSTIterator.hasNext(); // return True
        assert 15 == bSTIterator.next();    // return 15
        assert bSTIterator.hasNext(); // return True
        assert 20 == bSTIterator.next();    // return 20
        assert !bSTIterator.hasNext(); // return False
    }

    static class BSTIterator {

        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            TreeNode node = root;
            while (node.left != null) {
                stack.push(node);
                node = node.left;
            }
            stack.push(node);
        }

        public int next() {
            TreeNode node = stack.pop();
            int val = node.val;
            if (node.right != null) {
                node = node.right;
                while (node.left != null) {
                    stack.push(node);
                    node = node.left;
                }
                stack.push(node);
            }
            return val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

}
