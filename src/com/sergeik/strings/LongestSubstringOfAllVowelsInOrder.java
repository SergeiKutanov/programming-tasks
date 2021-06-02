package com.sergeik.strings;

/**
 * A string is considered beautiful if it satisfies the following conditions:
 *
 * Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
 * The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
 * For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu",
 * and "aaaeeeooo" are not beautiful.
 *
 * Given a string word consisting of English vowels, return the length of the longest beautiful substring of word.
 * If no such substring exists, return 0.
 *
 * A substring is a contiguous sequence of characters in a string.
 */
public class LongestSubstringOfAllVowelsInOrder {

    public static void main(String[] args) {

        assert 0 == countSolution("a");
        assert 5 == countSolution("aeeeiiiioooauuuaeiou");
        assert 13 == countSolution("aeiaaioaaaaeiiiiouuuooaauuaeiu");

        assert 0 == solution("a");
        assert 5 == solution("aeeeiiiioooauuuaeiou");
        assert 13 == solution("aeiaaioaaaaeiiiiouuuooaauuaeiu");
    }

    private static int countSolution(String word) {
        if (word.length() < 5)
            return 0;

        int max = 0;
        int cLength = 1;

        int vCount = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i - 1) > word.charAt(i)) {
                vCount = 1;
                cLength = 1;
            } else {
                if (word.charAt(i - 1) != word.charAt(i))
                    vCount++;
                cLength++;
            }
            if (vCount == 5)
                max = Math.max(cLength, max);
        }

        return max;
    }

    private static int solution(String word) {
        if (word.length() < 5)
            return 0;

        int max = 0;
        int start = 0;
        int end = 1;

        int allVowels = 1065233;
//        allVowels |= (1 << 'a' - 'a');
//        allVowels |= (1 << 'e' - 'a');
//        allVowels |= (1 << 'i' - 'a');
//        allVowels |= (1 << 'o' - 'a');
//        allVowels |= (1 << 'u' - 'a');
        int seen = (1 << word.charAt(start) - 'a');
        while (end < word.length()) {
            if (word.charAt(end) >= word.charAt(end - 1)) {
                seen |= (1 << word.charAt(end) - 'a');
                end++;
            } else {
                if (seen == allVowels)
                    max = Math.max(max, end - start);
                start = end;
                seen = (1 << word.charAt(start) - 'a');
                end++;
            }
        }
        if (seen == allVowels)
            max = Math.max(max, end - start);
        return max;
    }

}
