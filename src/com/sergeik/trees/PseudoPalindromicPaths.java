package com.sergeik.trees;

/**
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be
 * pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 */
public class PseudoPalindromicPaths {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        assert 2 == solution(root);
    }

    private static int solution(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode node, int mask) {
        if (node == null) {
            return 0;
        }
        mask ^= (1 << node.val);
        if (node.left == null && node.right == null)
            return isPalindrome(mask) ? 1 : 0;
        return dfs(node.left, mask) + dfs(node.right, mask);
    }

    private static boolean isPalindrome(int mask) {
        boolean found = false;
        while (mask > 0) {
            if ((mask & 1) == 1) {
                if (found)
                    return false;
                found = true;
            }
            mask >>= 1;
        }
        return true;
    }

}
