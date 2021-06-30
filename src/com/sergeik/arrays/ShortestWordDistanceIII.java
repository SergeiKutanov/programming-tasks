package com.sergeik.arrays;

import java.util.*;

/**
 * Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return
 * the shortest distance between these two words in the list.
 *
 * Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.
 */
public class ShortestWordDistanceIII {

    public static void main(String[] args) {
        assert 1 == solution(
                new String[] {"a","c","b","b","a"},
                "b",
                "a"
        );
        assert 1 == solution(
                new String[] {"practice", "makes", "perfect", "coding", "makes"},
                "makes",
                "coding"
        );
        assert 3 == solution(
                new String[] {"practice", "makes", "perfect", "coding", "makes"},
                "makes",
                "makes"
        );
    }

    private static int solution(String[] wordsDict, String word1, String word2) {
        Stack<Integer> w1Stack = new Stack<>(), w2Stack = new Stack<>();
        boolean equalWords = word1.equals(word2);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < wordsDict.length; i++) {
            boolean newIndex = false;
            if (wordsDict[i].equals(word1)) {
                newIndex = true;
                if (equalWords && !w1Stack.isEmpty())
                    res = Math.min(res, Math.abs(i - w1Stack.peek()));
                w1Stack.push(i);
            } else if (wordsDict[i].equals(word2)) {
                newIndex = true;
                w2Stack.push(i);
            }
            if (!equalWords && newIndex && !w1Stack.isEmpty() && !w2Stack.isEmpty()) {
                res = Math.min(res, Math.abs(w2Stack.peek() - w1Stack.peek()));
            }
        }
        return res;
    }

    private static int treeSetSolution(String[] wordsDict, String word1, String word2) {
        Map<String, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            map.computeIfAbsent(wordsDict[i], l -> new TreeSet<>());
            map.get(wordsDict[i]).add(i);
        }
        int res = Integer.MAX_VALUE;
        TreeSet<Integer> idxs1 = map.get(word1);
        TreeSet<Integer> idxs2 = map.get(word2);
        for (int idx1 : idxs1) {
            Integer idx2Higher = idxs2.higher(idx1);
            Integer idx2Lower = idxs2.lower(idx1);
            if (idx2Higher != null) {
                res = Math.min(res, Math.abs(idx1 - idx2Higher));
            }
            if (idx2Lower != null) {
                res = Math.min(res, Math.abs(idx1 - idx2Lower));
            }
            if (res == 1)
                return res;
        }
        return res;
    }

}
