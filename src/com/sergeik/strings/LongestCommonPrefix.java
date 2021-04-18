package com.sergeik.strings;


/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Solution implemented - vertical scanning.
 *
 * Other possible:
 * --------------
 * Horizontal scanning
 * --------------
 *
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = new String[]{"flower","flow","flight"};
        assert "fl".equals(solution(strs));

        strs = new String[]{"dog","racecar","car"};
        assert "".equals(solution(strs));

        strs = new String[]{};
        assert "".equals(solution(strs));
    }

    /**
     * Time - O(S) where S is the sum of all characters in the strings
     *
     * This approach uses vertical scanning. Scans each string characters at specific position.
     * Algorithm quits as soon as not matching character found
     *
     * @param strs
     * @return
     */
    private static String solution(String[] strs) {

        if (strs.length < 1) return "";

        StringBuilder prefix = new StringBuilder();
        int charIndex = 0;
        while (true) {
            if ((strs[0].length() - 1) < charIndex) return prefix.toString();
            char currentChar = strs[0].charAt(charIndex);
            for (int wordIndex = 1; wordIndex < strs.length; wordIndex++) {
                if ((strs[wordIndex].length() - 1) < charIndex) return prefix.toString();
                if (currentChar != strs[wordIndex].charAt(charIndex)) return prefix.toString();
            }
            prefix.append(currentChar);
            charIndex++;
        }
    }

    private static String longestCommonPrefix_horizontalScanning(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }



}
