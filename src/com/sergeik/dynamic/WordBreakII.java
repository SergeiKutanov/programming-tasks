package com.sergeik.dynamic;

import java.util.*;

/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word
 * is a valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
public class WordBreakII {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"pine apple pen apple", "pine applepen apple", "pineapple pen apple"},
                solution("pineapplepenapple", Arrays.asList("apple","pen","applepen","pine","pineapple")).toArray()
        );
        assert Arrays.equals(
                new String[] {},
                solution("catsandog", Arrays.asList("cat", "cats", "and", "sand", "dog")).toArray()
        );
        assert Arrays.equals(
                new String[] {"cat sand dog", "cats and dog"},
                solution("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")).toArray()
        );
    }

    private static List<String> solution(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> res = new LinkedList<>();
        dfs(s, 0, dict, res, new StringBuilder());
        return res;
    }

    private static void dfs(String s, int start, Set<String> dict, List<String> res, StringBuilder cRes) {
        if (start == s.length()) {
            res.add(cRes.toString());
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (dict.contains(str)) {
                int len = cRes.length();
                if (len > 0)
                    cRes.append(" ");
                cRes.append(str);
                dfs(s, i, dict, res, cRes);
                cRes.delete(len, cRes.length());
            }
        }
    }

}
