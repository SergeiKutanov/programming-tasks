package com.sergeik.strings;

/**
 * You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices
 * in a string (not necessarily different) and swap the characters at these indices.
 *
 * Return true if it is possible to make both strings equal by performing at most one string swap on exactly one
 * of the strings. Otherwise, return false.
 */
public class CheckIfOneStringSwapCanMaleStringEqual {

    public static void main(String[] args) {
        assert !solution("abcd", "dcba");
        assert solution("kelb", "kelb");
        assert solution("bank", "kanb");
        assert !solution("attack", "defend");
    }

    private static boolean solution(String s1, String s2) {
        int[] diffs = new int[4];
        int idx = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (idx == diffs.length)
                    return false;
                diffs[idx++] = s1.charAt(i) - 'a';
                diffs[idx++] = s2.charAt(i) - 'a';
            }
        }
        return diffs[0] == diffs[3] && diffs[1] == diffs[2];
    }

}
