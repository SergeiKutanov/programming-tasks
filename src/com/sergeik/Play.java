package com.sergeik;

import com.sergeik.trees.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class Play {

    private static boolean found = false;

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(3);
//        root.right = new TreeNode(6);
//        root.right.right = new TreeNode(7);
//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(4);
//
//        assert findTarget(root, 9);

        assert "".equals(gcdOfStrings("ABCDEF", "ABC"));
        assert "".equals(gcdOfStrings("LEET", "CODE"));
        assert "AB".equals(gcdOfStrings("ABABAB", "ABAB"));
        assert "ABC".equals(gcdOfStrings("ABCABC", "ABC"));

    }

    public static String gcdOfStrings(String str1, String str2) {
        String shorter = str1.length() > str2.length() ? str2 : str1;
        String longer = shorter.equals(str1) ? str2 : str1;
        int index = 0;
        while (index < shorter.length()) {
            if (shorter.charAt(index) == longer.charAt(index)) {
                index++;
            } else {
                break;
            }
        }

        if (index < shorter.length())
            return "";
        if (index < longer.length())
            return gcdOfStrings(shorter, longer.substring(index));
        if (index == longer.length())
            return shorter;
        return "";
    }

    public static boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        if (root == null)
            return false;
        trav(root, k, set);
        return found;
    }

    private static void trav(TreeNode node, int k, Set<Integer> set) {
        if (set.contains(k - node.val)) {
            found = true;
            return;
        }
        set.add(node.val);
        if (node.left != null)
            trav(node.left, k, set);
        if (node.right != null)
            trav(node.right, k, set);
    }

}
