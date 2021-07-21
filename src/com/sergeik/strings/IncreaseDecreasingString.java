package com.sergeik.strings;

/**
 * Given a string s. You should re-order the string using the following algorithm:
 *
 * Pick the smallest character from s and append it to the result.
 * Pick the smallest character from s which is greater than the last appended character to the result and append it.
 * Repeat step 2 until you cannot pick more characters.
 * Pick the largest character from s and append it to the result.
 * Pick the largest character from s which is smaller than the last appended character to the result and append it.
 * Repeat step 5 until you cannot pick more characters.
 * Repeat the steps from 1 to 6 until you pick all characters from s.
 * In each step, If the smallest or the largest character appears more than once you can choose any occurrence
 * and append it to the result.
 *
 * Return the result string after sorting s with this algorithm.
 */
public class IncreaseDecreasingString {

    public static void main(String[] args) {
        assert "art".equals(solution("rat"));
        assert "abccbaabccba".equals(solution("aaaabbbbcccc"));
    }

    private static String solution(String s) {
        StringBuilder res = new StringBuilder();
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a']++;
        int n = s.length();
        while (n > 0) {
            int i = 0;
            int added = 0;
            while (i < 26) {
                while (i < 26 && count[i] == 0)
                    i++;
                if (i < 26) {
                    res.append((char)('a' + i));
                    count[i]--;
                    i++;
                    added++;
                }

            }
            i = 25;
            while (i >= 0) {
                while (i >= 0 && count[i] == 0)
                    i--;
                if (i >= 0) {
                    res.append((char)('a' + i));
                    count[i]--;
                    i--;
                    added++;
                }
            }
            n -= added;
        }

        return res.toString();
    }

}
