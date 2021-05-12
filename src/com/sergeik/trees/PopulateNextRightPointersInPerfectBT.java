package com.sergeik.trees;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
 * should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 */
public class PopulateNextRightPointersInPerfectBT {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right = new Node(3);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        Node root = solution(head);

        assert root.left.next.equals(root.right);
        assert root.left.left.next.equals(root.left.right);
        assert root.left.right.next.equals(root.right.left);
        assert root.right.next == null;
        assert root.right.left.next.equals(root.right.right);

    }

    private static Node solution(Node root) {
        dfs(root, null);
        return root;
    }

    private static void dfs(Node current, Node next) {
        if (current == null)
            return;
        current.next = next;
        dfs(current.left, current.right);
        dfs(current.right, next == null ? null : next.left);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
