package com.sergeik.strings;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        assert solution(s, t);
        assert arraySolution(s, t);

        s = "rat";
        t = "car";
        assert !solution(s, t);
        assert !arraySolution(s, t);

        s = "ab";
        t = "a";
        assert !solution(s, t);
        assert !arraySolution(s, t);

        s = "test";
        t = "foob";
        assert !solution(s, t);
        assert !arraySolution(s, t);
    }

    /**
     * Time O(n)
     * Memory (n)
     * Slightly slow since hashmap is used
     * @param s
     * @param t
     * @return
     */
    private static boolean solution(String s, String t) {

        if (s.length() != t.length()) return false;

        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            charMap.put(
                    ch,
                    charMap.getOrDefault(ch, 0) + 1
            );
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (!charMap.containsKey(ch)) return false;
            int count = charMap.get(ch) - 1;
            if (count == 0) {
                charMap.remove(ch);
            } else {
                charMap.put(ch, count);
            }
        }

        if (charMap.size() > 0) return false;

        return true;
    }

    /**
     * Time O(n)
     * Memory O(1)
     * Takes less memory than HashMap and is faster since hashing algorithm is not performed.
     * @param s
     * @param t
     * @return
     */
    private static boolean arraySolution(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] charArray = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charArray[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            charArray[index]--;
            if (charArray[index] < 0) {
                return false;
            }
        }
        return true;
    }

}
