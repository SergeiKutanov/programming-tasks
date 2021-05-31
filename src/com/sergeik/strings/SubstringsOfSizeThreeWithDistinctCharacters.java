package com.sergeik.strings;

/**
 * A string is good if there are no repeated characters.
 *
 * Given a string s​​​​​, return the number of good substrings of length three in s​​​​​​.
 *
 * Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
 *
 * A substring is a contiguous sequence of characters in a string.
 */
public class SubstringsOfSizeThreeWithDistinctCharacters {

    public static void main(String[] args) {
        assert 1 == solution("xyzzaz");
        assert 4 == solution("aababcabc");
    }

    private static int solution(String s) {
        int counter = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            boolean[] chars = new boolean[26];
            int j = i;
            int sCounter = 0;
            for (; j < i + 3; j++) {
                if (chars[s.charAt(j) - 'a'])
                    break;
                chars[s.charAt(j) - 'a'] = true;
                sCounter++;
            }
            if (sCounter == 3)
                counter++;
        }
        return counter;
    }

}
