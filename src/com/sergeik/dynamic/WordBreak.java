package com.sergeik.dynamic;

import java.util.*;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into
 * a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
public class WordBreak {

    public static void main(String[] args) {
        assert !solution("catsandog", Arrays.asList("cats","dog","sand","and","cat"));
        assert solution("applepenapple", Arrays.asList("apple", "pen"));
        assert solution("leetcode", Arrays.asList("leet", "code"));
    }

    private static boolean solution(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return backtrack(s, dict);
    }


    private static boolean backtrack(String s, Set<String> dict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String w = s.substring(j, i);
                if (dp[j] && dict.contains(w)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
