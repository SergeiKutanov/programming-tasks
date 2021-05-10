package com.sergeik.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 */
public class LongestSubstringWithoutRepeatingChars {

    public static void main(String[] args) {
        assert 2 == solution("au");
        assert 3 == solution("abcabcbb");
        assert 1 == solution("bbbbb");
        assert 3 == solution("pwwkew");
        assert 1 == solution("a");
    }

    private static int solution(String s) {
        int longestStr = 0;
        int start = 0;
        int end = start;
        Set<Character> seenChars = new HashSet<>();
        while (end < s.length()) {
            if (!seenChars.contains(s.charAt(end))) {
                seenChars.add(s.charAt(end));
                end++;
                longestStr = Math.max(longestStr, end - start);
            } else {
                while (s.charAt(start) != s.charAt(end)) {
                    seenChars.remove(s.charAt(start));
                    start++;
                }
                seenChars.remove(s.charAt(start));
                start++;
            }
        }

        return longestStr;
    }

}
