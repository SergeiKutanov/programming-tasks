package com.sergeik.strings;

import java.util.*;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 */
public class IsomorphicStrings {

    public static void main(String[] args) {
        assert !solution("bbbaaaba", "aaabbbba");
        assert solution("egg", "add");
        assert !solution("foo", "bar");
        assert solution("paper", "title");
    }

    private static boolean solution(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sCh = s.charAt(i);
            char tCh = t.charAt(i);
            if (map.containsKey(tCh)) {
                if (map.get(tCh).equals(sCh))
                    continue;
                else
                    return false;
            } else {
                if (!map.containsValue(sCh))
                    map.put(tCh, sCh);
                else
                    return false;
            }
        }
        return true;
    }

}
