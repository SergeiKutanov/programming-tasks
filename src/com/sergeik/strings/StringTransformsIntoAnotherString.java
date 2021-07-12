package com.sergeik.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing
 * zero or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 */
public class StringTransformsIntoAnotherString {

    public static void main(String[] args) {
        assert solution("abcdefghijklmnopqrstuvwxy", "bcdefghijkamnopqrstuvwxyz");
        assert solution("abcdefghijklmnopqrstuvwxyz", "bcadefghijklmnopqrstuvwxzz");
        assert !solution("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza");
        assert !solution("aa", "ab");
        assert solution("aabcc", "ccdee");
        assert !solution("leetcode", "codeleet");
    }

    private static boolean hashMapSolution(String str1, String str2) {
        if (str1.equals(str2))
            return true;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str2.length(); i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);
            if (map.getOrDefault(ch1, ch2) != ch2)
                return false;
            map.put(ch1, ch2);
        }
        return new HashSet<>(map.values()).size() < 26;
    }

    private static boolean solution(String str1, String str2) {
        if (str1.equals(str2))
            return true;
        int[] map = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0; i < str1.length(); i++) {
            int ch1 = str1.charAt(i) - 'a';
            int ch2 = str2.charAt(i) - 'a';
            if (map[ch1] != -1 && map[ch1] != ch2)
                return false;
            map[ch1] = ch2;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int n: map) {
            if (n == -1)
                return true;
            set.add(n);
        }
        return set.size() < 26;
    }

}
