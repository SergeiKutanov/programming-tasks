package com.sergeik.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t.
 * If there is no such window in s that covers all characters in t, return the empty string "".
 *
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        assert "a".equals(solution("ab", "a"));
        assert "".equals(solution("a", "aa"));
        assert "".equals(solution("a", "b"));
        assert "a".equals(solution("a", "a"));
        assert "ab".equals(solution("bdab", "ab"));
        assert "BANC".equals(solution("ADOBECODEBANC", "ABC"));

        assert "a".equals(optSolution("ab", "a"));
        assert "".equals(optSolution("a", "aa"));
        assert "".equals(optSolution("a", "b"));
        assert "a".equals(optSolution("a", "a"));
        assert "ab".equals(optSolution("bdab", "ab"));
        assert "BANC".equals(optSolution("ADOBECODEBANC", "ABC"));
    }
    
    private static String optSolution(String s, String t) {
        if (s.length() == 0 || t.length() == 0)
            return "";

        Map<Character, Integer> tMap = new HashMap<>();
        for (char ch: t.toCharArray())
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);

        int validCharactersCount = 0;
        Map<Character, Integer> charCountMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int[] result = new int[]{-1, 0, 0};

        while (end < s.length()) {
            char ch = s.charAt(end);
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
            if (tMap.containsKey(ch) && charCountMap.get(ch).intValue() == tMap.get(ch).intValue()) {
                validCharactersCount++;
            }

            while (start <= end && validCharactersCount == tMap.size()) {
                if (result[0] == -1 || end - start + 1 < result[0]) {
                    result[0] = end - start + 1;
                    result[1] = start;
                    result[2] = end;
                }
                ch = s.charAt(start);
                charCountMap.put(ch, charCountMap.get(ch) - 1);
                if (tMap.containsKey(ch) && charCountMap.get(ch).intValue() < tMap.get(ch).intValue())
                    validCharactersCount--;
                start++;
            }
            end++;
        }

        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }

    private static String solution(String s, String t) {
        Map<Character, Integer> tCounter = buildCharMap(t);
        String window = "";
        int start = 0;
        int end = t.length();
        if (end > s.length())
            return window;

        boolean extend = true;
        String firstWindow = s.substring(start, end);
        Map<Character, Integer> windowMap = buildCharMap(firstWindow);
        if (isValid(windowMap, tCounter)) {
            window = firstWindow;
            extend = false;
        }

        while (true) {
            if (extend) {
                end++;
            } else {
                start++;
            }
            if (end == s.length() + 1 || (end - start < t.length() && end == s.length()))
                return window;
            String newWindow = s.substring(start, end);
            //append new char to map
            if (extend) {
                char newCh = s.charAt(end - 1);
                windowMap.put(newCh, windowMap.getOrDefault(newCh, 0) + 1);
            } else {
                char ch = s.charAt(start - 1);
                if (windowMap.get(ch) == 1) {
                    windowMap.remove(ch);
                } else {
                    windowMap.put(ch, windowMap.get(ch) - 1);
                }
            }

            //remove character from map
            if (isValid(windowMap, tCounter)) {
                if ("".equals(window) || window.length() > newWindow.length()) {
                    window = newWindow;
                }
                extend = false;
            } else {
                extend = true;
            }
        }
    }

    private static boolean isValid(Map<Character, Integer> s, Map<Character, Integer> t) {
        for (char ch: t.keySet()) {
            if (!s.containsKey(ch))
                return false;
            if (t.get(ch) > s.get(ch))
                return false;
        }
        return true;
    }

    private static Map<Character, Integer> buildCharMap(String s) {
        Map<Character, Integer> tCounter = new HashMap<>();
        for (char ch: s.toCharArray()) {
            tCounter.put(ch, tCounter.getOrDefault(ch, 0) + 1);
        }
        return tCounter;
    }

}
