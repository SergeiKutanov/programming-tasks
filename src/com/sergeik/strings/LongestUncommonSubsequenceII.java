package com.sergeik.strings;

import java.util.*;

/**
 * Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the
 * longest uncommon subsequence does not exist, return -1.
 *
 * An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not
 * the others.
 *
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 *
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get
 * "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 */
public class LongestUncommonSubsequenceII {

    public static void main(String[] args) {
        assert 2 == solution(new String[] {"aabbcc", "aabbcc","cb","abc"});
        assert -1 == solution(new String[] {"aaa","aaa","aa"});
        assert 3 == solution(new String[] {"aba", "cdc", "eae"});
    }

    private static int solution(String[] strs) {
        Map<String, Integer> count = new HashMap<>();
        for (String str: strs) {
            String[] subSequences = getSubsequences(str);
            for (String subSequence: subSequences)
                count.put(subSequence, count.getOrDefault(subSequence, 0) + 1);
        }

        int res = -1;
        for (String subSequence: count.keySet()) {
            if (count.get(subSequence) == 1)
                res = Math.max(res, subSequence.length());
        }

        return res;
    }

    private static String[] getSubsequences(String str) {
        Set<String> set = new HashSet<>();
        if (str.length() == 0)
            set.add("");
        else {
            String[] prev = getSubsequences(str.substring(1));
            for (String sPrev: prev) {
                set.add(sPrev);
                set.add(str.charAt(0) + sPrev);
            }
        }
        return set.toArray(new String[set.size()]);
    }

}
