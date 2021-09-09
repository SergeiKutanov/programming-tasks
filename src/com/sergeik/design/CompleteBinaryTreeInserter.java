package com.sergeik.design;

import com.sergeik.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled,
 * and all nodes are as far left as possible.
 *
 * Design an algorithm to insert a new node to a complete binary tree keeping it complete after the insertion.
 *
 * Implement the CBTInserter class:
 *
 * CBTInserter(TreeNode root) Initializes the data structure with the root of the complete binary tree.
 * int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete,
 * and returns the value of the parent of the inserted TreeNode.
 * TreeNode get_root() Returns the root node of the tree.
 */
public class CompleteBinaryTreeInserter {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        CBTInserter cBTInserter = new CBTInserter(root);
        assert 1 == cBTInserter.insert(3);  // return 1
        assert 2 == cBTInserter.insert(4);  // return 2
        assert root == cBTInserter.get_root(); // return [1, 2, 3, 4]
    }

    static class CBTInserter {

        TreeNode root;
        Queue<TreeNode> queue;

        public CBTInserter(TreeNode root) {
            this.root = root;
            queue = new LinkedList<>();
            queue.offer(root);
            while (queue.peek().left != null || queue.peek().right != null) {
                TreeNode node = queue.peek();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                if (node.left != null && node.right != null)
                    queue.poll();
                else
                    break;
            }
        }

        public int insert(int val) {
            TreeNode node = queue.peek();
            TreeNode newNode = new TreeNode(val);
            if (node.left == null) {
                node.left = newNode;
            } else {
                node.right = newNode;
                queue.poll();
            }
            queue.offer(newNode);
            return node.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }

}
