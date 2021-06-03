package com.sergeik.strings;

/**
 * You are given two strings word1 and word2. You want to construct a string merge in the following way:
 * while either word1 or word2 are non-empty, choose one of the following options:
 *
 * If word1 is non-empty, append the first character in word1 to merge and delete it from word1.
 * For example, if word1 = "abc" and merge = "dv", then after choosing this operation, word1 = "bc" and merge = "dva".
 * If word2 is non-empty, append the first character in word2 to merge and delete it from word2.
 * For example, if word2 = "abc" and merge = "", then after choosing this operation, word2 = "bc" and merge = "a".
 * Return the lexicographically largest merge you can construct.
 *
 * A string a is lexicographically larger than a string b (of the same length) if in the first position
 * where a and b differ, a has a character strictly larger than the corresponding character in b. For example,
 * "abcd" is lexicographically larger than "abcc" because the first position they differ is at the fourth character,
 * and d is greater than c.
 */
public class LargestMergeOfTwoStrings {

    public static void main(String[] args) {
            assert "abdcabcabcaba".equals(solution("abcabc", "abdcaba"));
    }

    private static String recSolution(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0)
            return word1 + word2;
        if (word1.compareTo(word2) > 0) {
            return word1.charAt(0) + recSolution(word1.substring(1), word2);
        }
        return word2.charAt(0) + recSolution(word1, word2.substring(1));
    }

    private static String solution(String word1, String word2) {
        StringBuilder res = new StringBuilder();
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < word1.length() && idx2 < word2.length()) {
            if (word1.charAt(idx1) < word2.charAt(idx2)) {
                res.append(word2.charAt(idx2++));
            } else if (word1.charAt(idx1) > word2.charAt(idx2)) {
                res.append(word1.charAt(idx1++));
            } else {
                String s1 = word1.substring(idx1);
                String s2 = word2.substring(idx2);
                if (s1.compareTo(s2) > 0) {
                    res.append(word1.charAt(idx1++));
                } else {
                    res.append(word2.charAt(idx2++));
                }
            }
        }
        if (idx1 < word1.length())
            res.append(word1.substring(idx1));
        if (idx2 < word2.length())
            res.append(word2.substring(idx2));

        return res.toString();
    }

}
