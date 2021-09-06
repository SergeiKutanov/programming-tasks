package com.sergeik.arrays;

import java.util.*;

/**
 * Given a sentence text (A sentence is a string of space-separated words) in the following format:
 *
 * First letter is in upper case.
 * Each word in text are separated by a single space.
 * Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their
 * lengths. If two words have the same length, arrange them in their original order.
 *
 * Return the new text following the format shown above.
 *
 *
 */
public class RearrangeWordsInASentence {

    public static void main(String[] args) {
        assert "To be or to be not".equals(solution("To be or not to be"));
        assert "On and keep calm code".equals(solution("Keep calm and code on"));
    }

    private static String solution(String text) {
        String[] words = text.toLowerCase().split(" ");
        Arrays.sort(words, (a,b) -> a.length() - b.length());
        String res = String.join(" ", words);
        return Character.toUpperCase(res.charAt(0)) + res.substring(1);
    }

    private static String solutionWithMap(String text) {
        String[] words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i] + "_" + i;
        }
        Arrays.sort(words, (a, b) -> {
            String[] w1 = a.split("_");
            String[] w2 = b.split("_");
            if (w1[0].length() == w2[0].length()) {
                return Integer.parseInt(w1[1]) - Integer.parseInt(w2[1]);
            }
            return w1[0].length() - w2[0].length();
        });
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String w = words[i].split("_")[0];
            if (i == 0)
                res.append(w.substring(0,1).toUpperCase() + w.substring(1));
            else {
                res.append(" ").append(w.toLowerCase());
            }
        }
        return res.toString();
    }

}
