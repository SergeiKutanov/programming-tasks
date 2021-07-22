package com.sergeik.strings;

import java.util.Arrays;

/**
 * A permutation perm of n + 1 integers of all the integers in the range [0, n] can be represented as a string
 * s of length n where:
 *
 * s[i] == 'I' if perm[i] < perm[i + 1], and
 * s[i] == 'D' if perm[i] > perm[i + 1].
 * Given a string s, reconstruct the permutation perm and return it. If there are multiple valid permutations perm,
 * return any of them.
 */
public class DIStringMatch {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {0,4,1,3,2}, solution("IDID"));
    }

    private static int[] solution(String s) {
        int[] res = new int[s.length() + 1];
        int left = 0, right = s.length();
        for (int i = 0; i < s.length(); i++) {
            res[i] = s.charAt(i) == 'I' ? left++ : right--;
        }
        res[s.length()] = left;
        return res;
    }

}
