package com.sergeik.design;

import com.sergeik.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not
 * necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 */
public class SerializeDeserializeBinaryTree {

    public static void main(String[] args) {

        Codec codec = new Codec();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        String serialized = codec.serialize(root);
        TreeNode newRoot = codec.deserialize(serialized);

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        serialized = codec.serialize(root);
        TreeNode deserialized = codec.deserialize(serialized);
        String str = "";
    }

    public static class Codec {

        private static final String divider = ",";
        private static final String NN = "X";


        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            buildString(root, stringBuilder);
            return stringBuilder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> nodes = new LinkedList<>();
            for (String d: data.split(divider))
                nodes.add(d);
            return buildTree(nodes);
        }

        private TreeNode buildTree(Queue<String> nodes) {
            String val = nodes.remove();
            if (val.equals(NN))
                return null;
            else {
                TreeNode node = new TreeNode(Integer.valueOf(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }

        private void buildString(TreeNode node, StringBuilder stringBuilder) {
            if (node == null) {
                stringBuilder.append(NN).append(divider);
            } else {
                stringBuilder.append(node.val).append(divider);
                buildString(node.left, stringBuilder);
                buildString(node.right, stringBuilder);
            }
        }
    }



}
