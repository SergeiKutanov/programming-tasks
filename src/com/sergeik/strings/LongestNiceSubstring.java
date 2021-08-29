package com.sergeik.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and
 * lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not
 * because 'b' appears, but 'B' does not.
 *
 * Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of
 * the earliest occurrence. If there are none, return an empty string.
 */
public class LongestNiceSubstring {

    public static void main(String[] args) {
        assert "dD".equals(solution("dDzeE"));
        assert "".equals(solution("c"));
        assert "Bb".equals(solution("Bb"));
        assert "aAa".equals(solution("YazaAay"));
    }

    private static String solution(String s) {
        String res = "";
        for (int i = 0; i < s.length() - 1; i++) {
            int val = s.charAt(i);
            int lCase = 0, uCase = 0;
            if (Character.isUpperCase(val)) {
                val -= 'A';
                uCase |= 1 << val;
            } else {
                val -= 'a';
                lCase |= 1 << val;
            }
            for (int j = i + 1; j < s.length(); j++) {
                val = s.charAt(j);
                if (Character.isUpperCase(val)) {
                    val -= 'A';
                    uCase |= 1 << val;
                } else {
                    val -= 'a';
                    lCase |= 1 << val;
                }
                if (lCase == uCase && res.length() < j + 1 - i)
                    res = s.substring(i, j + 1);
            }
        }
        return res;
    }

    private static String setSolution(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            Set<Character> seen = new HashSet<>();
            char ch = s.charAt(i);
            int val;
            if (ch >= 'a' && ch <= 'z')
                val = ch - 'a';
            else
                val = ch - 'A';
            seen.add(ch);
            int mask = 1 << val;
            for (int j = i + 1; j < s.length(); j++) {
                char nextChar = s.charAt(j);
                if (!seen.contains(nextChar)) {
                    seen.add(nextChar);
                    int nextVal;
                    if (nextChar >= 'a' && nextChar <= 'z')
                        nextVal = nextChar - 'a';
                    else
                        nextVal = nextChar - 'A';
                    mask ^= 1 << nextVal;
                }
                if (mask == 0 && res.length() < j - i + 1)
                    res = s.substring(i, j + 1);
            }
        }
        return res;
    }

}
