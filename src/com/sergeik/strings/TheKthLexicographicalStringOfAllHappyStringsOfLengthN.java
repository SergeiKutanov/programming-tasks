package com.sergeik.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * A happy string is a string that:
 *
 * consists only of letters of the set ['a', 'b', 'c'].
 * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc"
 * are not happy strings.
 *
 * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
 *
 * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
 */
public class TheKthLexicographicalStringOfAllHappyStringsOfLengthN {

    public static void main(String[] args) {
        assert "abacbabacb".equals(solution(10, 100));
        assert "cab".equals(solution(3, 9));
    }

    private static String solution(int n, int k) {
        List<String> words = new ArrayList<>();
        dfs(n, k, new StringBuilder(), words, new char[] {'a', 'b', 'c'});
        String res = "";
        if (k - 1 < words.size())
            res = words.get(k - 1);
        return res;
    }

    private static void dfs(int n, int k, StringBuilder sb, List<String> list, char[] letters) {
        if (list.size() == k)
            return;
        if (sb.length() == n) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < letters.length; i++) {
            char ch = letters[i];
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ch)
                continue;
            sb.append(ch);
            dfs(n, k, sb, list, letters);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
