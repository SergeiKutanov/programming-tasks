package com.sergeik.trees;

/**
 * One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node, we record
 * the node's value. If it is a null node, we record using a sentinel value such as '#'.
 *
 *
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#'
 * represents a null node.
 *
 * Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization
 * of a binary tree.
 *
 * It is guaranteed that each comma-separated value in the string must be either an integer or a character '#'
 * representing null pointer.
 *
 * You may assume that the input format is always valid.
 *
 * For example, it could never contain two consecutive commas, such as "1,,3".
 * Note: You are not allowed to reconstruct the tree.
 */
public class VerifyPreorderSerializationOfABinaryTree {

    public static void main(String[] args) {
        assert solution("9,#,92,#,#");
        assert !solution("9,#,#,1");
        assert !solution("1,#");
        assert solution("9,3,4,#,#,1,#,#,2,#,6,#,#");
    }

    private static boolean solution(String preorder) {
        return preorder.length() == preOrder(preorder, 0) + 1;
    }

    private static int preOrder(String s, int idx) {
        if (idx >= s.length())
            return idx;
        char ch = s.charAt(idx);
        if (ch == '#')
            return idx;
        int nextIdx = s.indexOf(',', idx);
        idx = preOrder(s, nextIdx == -1 ? s.length() : nextIdx + 1);
        nextIdx = s.indexOf(',', idx);
        idx = preOrder(s, nextIdx == -1 ? s.length() : nextIdx + 1);
        return idx;
    }

    private static boolean arraySolution(String preorder) {
        String[] nodes = preorder.split(",");
        return nodes.length == preOrder(nodes, 0) + 1;
    }

    private static int preOrder(String[] s, int idx) {
        if (idx >= s.length)
            return idx;
        String ch = s[idx];
        if (!ch.equals("#")) {
            idx = preOrder(s, idx + 1);
            idx = preOrder(s, idx + 1);
        }
        return idx;
    }

}
