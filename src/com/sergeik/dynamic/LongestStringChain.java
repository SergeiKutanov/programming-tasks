package com.sergeik.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1
 * to make it equal to word2. For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor
 * of word_2, word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 */
public class LongestStringChain {

    public static void main(String[] args) {
        assert 4 == solution(new String[]{"ba","b","a","bca","bda","bdca"});
        assert 5 == solution(new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"});
    }

    private static int solution(String[] words) {
        if (words == null)
            return 0;
        if (words.length <= 1)
            return words.length;

        Map<String, Integer> lengths = new HashMap<>();
        for (String w: words)
            lengths.put(w, null);

        int maxLength = 0;
        for (String w: words) {
            int currentLength = getMaxLength(w, lengths);
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    private static int getMaxLength(String w, Map<String, Integer> lengths) {
        if (lengths.get(w) != null)
            return lengths.get(w);

        if (w.length() <= 1) {
            return 1;
        }

        int length = 1;
        for (int i = 0; i < w.length(); i++) {
            String key = w.substring(0, i) + w.substring(i + 1);
            if (lengths.containsKey(key)) {
                int cLen = getMaxLength(key, lengths) + 1;
                length = Math.max(length, cLen);
            }
        }
        lengths.put(w, length);

        return lengths.get(w);
    }

}
