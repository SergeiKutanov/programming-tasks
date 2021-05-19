package com.sergeik.dynamic;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 */
public class InterleavingString {

    public static void main(String[] args) {
        assert solution("aabcc", "dbbca", "aadbbcbcac");
        assert !solution("aabcc", "dbbca", "aadbbbaccc");
    }

    private static boolean solution(String s1, String s2, String s3) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        if (s1Length + s2Length != s3.length())
            return false;
        return dfs(s1, s2, s3, 0, 0, 0, new boolean[s1Length + 1][s2Length + 1]);
    }

    private static boolean dfs(String s1, String s2, String s3, int i, int j, int k, boolean[][] invalid) {
        if (invalid[i][j])
            return false;
        if (k == s3.length())
            return true;
        boolean valid = i < s1.length() && s1.charAt(i) == s3.charAt(k) && dfs(s1, s2, s3, i + 1, j, k + 1, invalid) ||
                j < s2.length() && s2.charAt(j) == s3.charAt(k) && dfs(s1, s2, s3, i, j + 1, k + 1, invalid);
        if (!valid)
            invalid[i][j] = true;
        return valid;
    }

}
