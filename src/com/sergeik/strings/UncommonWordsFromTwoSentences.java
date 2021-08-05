package com.sergeik.strings;

import java.util.*;

/**
 * A sentence is a string of single-space separated words where each word consists only of lowercase letters.
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.
 */
public class UncommonWordsFromTwoSentences {

    public static void main(String[] args) {
        assert Arrays.equals(new String[] {"sweet", "sour"}, solution("this apple is sweet", "this apple is sour"));
    }

    private static String[] solution(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        for (String w: s1.split(" "))
            map.put(w, map.getOrDefault(w, 0) + 1);
        for (String w: s2.split(" "))
            map.put(w, map.getOrDefault(w, 0) + 1);
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> e: map.entrySet()) {
            if (e.getValue() == 1)
                res.add(e.getKey());
        }
        return res.toArray(new String[res.size()]);
    }

}
