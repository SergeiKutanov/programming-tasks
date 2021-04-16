package com.sergeik.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s, return the first non-repeating character in it and return its index.
 * If it does not exist, return -1.
 */
public class FirstUniqueCharacter {

    public static void main(String[] args) {
        String s = "leetcode";
        assert 0 == solution(s);
        assert 0 == cleanerSolution(s);

        s = "loveleetcode";
        assert 2 == solution(s);
        assert 2 == cleanerSolution(s);

        s = "aabb";
        assert -1 == solution(s);
        assert -1 == cleanerSolution(s);
    }

    /**
     * Time O(n)
     * Memory O(1) - number of possible characters (26 in alphabet)
     * @param s
     * @return
     */
    private static int cleanerSolution(String s) {
        Map<Character, Integer> charCounts = new HashMap<>();
        int stringLength = s.length();
        for (int i = 0; i < stringLength; i++) {
            char ch = s.charAt(i);
            charCounts.put(ch, charCounts.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < stringLength; i++) {
            if (charCounts.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    private static int solution(String s) {
        Map<Character, Integer> uniques = new HashMap<>();
        Set<Character> duplicates = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            //skip if already in duplicates
            if (duplicates.contains(ch)) continue;
            //if first occurance of duplicate
            if (uniques.containsKey(ch)) {
                uniques.remove(ch);
                duplicates.add(ch);
            } else {
                //unique
                uniques.put(ch, i);
            }
        }
        if (uniques.isEmpty()) return -1;
        int position = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> pair : uniques.entrySet()) {
            if (pair.getValue() < position) {
                position = pair.getValue();
            }
        }
        return position;
    }

}
