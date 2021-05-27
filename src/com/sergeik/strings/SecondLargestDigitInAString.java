package com.sergeik.strings;

/**
 * Given an alphanumeric string s, return the second largest numerical digit that appears in s,
 * or -1 if it does not exist.
 *
 * An alphanumeric string is a string consisting of lowercase English letters and digits.
 */
public class SecondLargestDigitInAString {

    public static void main(String[] args) {
        assert 4 == solution("sjhtz8344");
        assert 2 == solution("dfa12321afd");
        assert -1 == solution("abc1111");
        assert 0 == solution("ck077");
    }

    private static int solution(String s) {
        int max = -1;
        int sMax = -1;
        for (int i = 0; i < s.length(); i++) {
            int v = s.charAt(i) - '0';
            if (v >= 0 && v <= 9) {
                if (v > max) {
                    if (sMax < max)
                        sMax = max;
                    max = v;
                }
                if (v > sMax && v < max)
                    sMax = v;
            }
        }
        return sMax;
    }

}
