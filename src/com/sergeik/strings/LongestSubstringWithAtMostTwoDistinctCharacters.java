package com.sergeik.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

    public static void main(String[] args) {
        assert 3 == solution("eceba");
        assert 5 == solution("ccaabbb");
    }

    private static int solution(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        int left = 0, right;
        for (right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (map.size() < 2 || map.containsKey(ch)) {
                map.put(ch, right);
                res = Math.max(res, right - left + 1);
            } else {
                char prevChar = s.charAt(right - 1);
                while (s.charAt(left) == prevChar || map.get(s.charAt(left))!= left) {
                    left++;
                }
                map.remove(s.charAt(left));
                left++;
                map.put(ch, right);
                res = Math.max(res, right - left + 1);
            }
        }
        return res;
    }

}
