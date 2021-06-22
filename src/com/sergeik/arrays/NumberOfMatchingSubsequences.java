package com.sergeik.arrays;

import java.util.*;

/**
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 */
public class NumberOfMatchingSubsequences {

    public static void main(String[] args) {
        assert 2 == solution("dsahjpjauf", new String[] {"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"});
        assert 3 == solution("abcde", new String[] {"a","bb","acd","ace"});
    }

    private static int solution(String s, String[] words) {
        int res = 0;
        Map<Character, List<Queue<Character>>> wordsMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!wordsMap.containsKey(words[i].charAt(0))) {
                wordsMap.put(words[i].charAt(0), new LinkedList<>());
            }
            Queue<Character> chars = new LinkedList<>();
            for (int j = 1; j < words[i].length(); j++)
                chars.offer(words[i].charAt(j));
            wordsMap.get(words[i].charAt(0)).add(chars);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (wordsMap.containsKey(ch)) {
                List<Queue<Character>> charMaps = wordsMap.get(ch);
                wordsMap.remove(ch);
                for (Queue<Character> charMap: charMaps) {
                    if (charMap.isEmpty()) {
                        res++;
                    } else {
                        char firstChar = charMap.poll();
                        if (!wordsMap.containsKey(firstChar))
                            wordsMap.put(firstChar, new LinkedList<>());
                        wordsMap.get(firstChar).add(charMap);
                    }
                }
            }
        }

        return res;
    }

    private static int queueSolution(String s, String[] words) {
        int res = 0;
        Queue<int[]> idxs = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            idxs.offer(new int[] {i, 0});
        }
        for (int i = 0; i < s.length(); i++) {
            int len = idxs.size();
            for (int j = 0; j < len; j++) {
                int[] idx = idxs.poll();
                if (s.charAt(i) == words[idx[0]].charAt(idx[1])) {
                    idx[1]++;
                }
                if (idx[1] == words[idx[0]].length())
                    res++;
                else
                    idxs.offer(idx);
            }
        }
        return res;
    }

}
