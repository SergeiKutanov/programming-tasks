package com.sergeik.strings;

/**
 * You are given a string s of lowercase English letters and an integer array shifts of the same length.
 *
 * Call the shift() of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 *
 * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 * Now for each shifts[i] = x, we want to shift the first i + 1 letters of s, x times.
 *
 * Return the final string after all such shifts to s are applied.
 */
public class ShiftingLetters {

    public static void main(String[] args) {
        assert "gfd".equals(solution("aaa", new int[] {1,2,3}));
        assert "rpl".equals(solution("abc", new int[] {3,5,9}));
    }

    private static String solution(String s, int[] shifts) {
        StringBuilder res = new StringBuilder();
        int cur = 0;
        for (int i = shifts.length - 1; i >= 0; i--) {
            cur += shifts[i];
            int ch = (s.charAt(i) - 'a' + cur) % 26 + 'a';
            res.insert(0, (char)ch);
        }
        return res.toString();
    }

}
