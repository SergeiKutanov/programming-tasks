package com.sergeik.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern.
 * You may return the answer in any order.
 *
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every
 * letter x in the pattern with p(x), we get the desired word.
 *
 * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another
 * letter, and no two letters map to the same letter.
 */
public class FindAndReplacePattern {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"a", "b", "c"},
                solution(new String[] {"a", "b", "c"}, "a").toArray()
        );
        assert Arrays.equals(
                new String[] {"mee", "aqq"},
                solution(new String[] {"abc","deq","mee","aqq","dkd","ccc"}, "abb").toArray()
        );
    }

    private static List<String> solution(String[] words, String pattern) {
        List<String> res = new LinkedList<>();
        for (String w: words)
            if (canMap(w, pattern))
                res.add(w);
        return res;
    }

    private static boolean canMap(String s1, String s2) {
        boolean[] assigned = new boolean[26];
        if (s1.length() != s2.length())
            return false;
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char nextChar = s1.charAt(i);
            if (map.containsKey(s2.charAt(i))) {
                nextChar = map.get(s2.charAt(i));
            }
            if (nextChar != s1.charAt(i))
                return false;
            if (!map.containsKey(s2.charAt(i)) && assigned[s1.charAt(i) - 'a'])
                return false;
            if (!map.containsKey(s2.charAt(i))) {
                map.put(s2.charAt(i), s1.charAt(i));
                assigned[s1.charAt(i) - 'a'] = true;
            }
        }
        return true;
    }

}
