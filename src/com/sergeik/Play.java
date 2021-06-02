package com.sergeik;

public class Play {

    public static void main(String[] args) {

        assert interleaving("aabcc", "dbbca", "aadbbcbcac");

    }

    private static boolean interleaving(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        return dfs(s1, s2, s3, 0, 0, 0, new boolean[s1.length() + 1][s2.length() + 1]);
    }

    private static boolean dfs(String s1, String s2, String s3, int idx1, int idx2, int idx3, boolean[][] memo) {
        if (memo[idx1][idx2])
            return false;
        if (idx3 == s3.length())
            return true;
        boolean valid = (idx1 < s1.length() && s3.charAt(idx3) == s1.charAt(idx1) && dfs(s1, s2, s3, idx1 + 1, idx2, idx3 + 1, memo))
                || (idx2 < s2.length() && s3.charAt(idx3) == s2.charAt(idx2) && dfs(s1, s2, s3, idx1, idx2 + 1, idx3 + 1, memo));
        if (!valid)
            memo[idx1][idx2] = true;
        return valid;
    }

}
