package com.sergeik.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j])
 * where the two words do not share common letters. If no such two words exist, return 0.
 */
public class MaximumProductOfWordLengths {

    public static void main(String[] args) {
        assert 16 == bitSolution(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"});
        assert 4 == bitSolution(new String[]{"a","ab","abc","d","cd","bcd","abcd"});
        assert 0 == bitSolution(new String[]{"a", "aa", "aaa", "aaaa"});

        assert 16 == solution(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"});
        assert 4 == solution(new String[]{"a","ab","abc","d","cd","bcd","abcd"});
        assert 0 == solution(new String[]{"a", "aa", "aaa", "aaaa"});
    }

    private static int bitSolution(String[] words) {
        int[] val = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            for (int j = 0; j < s.length(); j++) {
                // a 1->1
                // b 2->10
                // c 4->100
                // ab 3->11
                // ac 5->101
                // abc 7->111
                // az 33554433->10000000000000000000000001
                val[i] |= 1 << (s.charAt(j) - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < val.length - 1; i++) {
            for (int j = i + 1; j < val.length; j++) {
                int commonChars = val[i] & val[j];
                if (commonChars == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }

    private static int solution(String[] words) {
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        Map<String, boolean[]> map = new HashMap<>();

        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String w1 = words[i];
                String w2 = words[j];
                int product = w1.length() * w2.length();
                if (product > max) {
                    boolean[] w1chars = map.get(w1);
                    if (w1chars == null) {
                        w1chars = buildChars(w1);
                        map.put(w1, w1chars);
                    }
                    boolean[] w2chars = map.get(w2);
                    if (w2chars == null) {
                        w2chars = buildChars(w2);
                        map.put(w2, w2chars);
                    }
                    if (compareChars(w1chars, w2chars))
                        max = product;
                } else {
                    break;
                }
            }
        }
        return max;
    }

    private static boolean compareChars(boolean[] ch1, boolean[] ch2) {
        for (int i = 0; i < ch1.length; i++) {
            if (ch1[i] && ch2[i])
                return false;
        }
        return true;
    }

    private static boolean[] buildChars(String w) {
        boolean[] chars = new boolean[26];
        for (int i = 0; i < w.length(); i++) {
            chars[w.charAt(i) - 'a'] = true;
        }
        return chars;
    }

}
