package com.sergeik.strings;

/**
 * There is a special typewriter with lowercase English letters 'a' to 'z' arranged in a circle with a pointer.
 * A character can only be typed if the pointer is pointing to that character. The pointer is initially pointing
 * to the character 'a'.
 *
 * Each second, you may perform one of the following operations:
 *
 * Move the pointer one character counterclockwise or clockwise.
 * Type the character the pointer is currently on.
 * Given a string word, return the minimum number of seconds to type out the characters in word.
 *
 */
public class MinimumTimeToTypeWordUsingSpecialTypewriter {

    public static void main(String[] args) {
        assert 34 == diffSolution("zjpc");
        assert 5 == solution("abc");
        assert 7 == diffSolution("bza");
    }

    private static int diffSolution(String word) {
        int cnt = word.length();
        char prev = 'a';
        for (int i = 0; i < word.length(); ++i) {
            char cur = word.charAt(i);
            int diff = Math.abs(cur - prev);
            cnt += Math.min(diff, 26 - diff);
            prev = cur;
        }
        return cnt;
    }

    private static int solution(String word) {
        int res = 0;
        int cur = 0;
        for (int i = 0; i < word.length(); i++) {
            int ch = word.charAt(i) - 'a';
            int ff = ch >= cur ? ch - cur : 26 - cur + ch;
            int rr = cur >= ch ? cur - ch : cur + 26 - ch;
            res += Math.min(ff, rr);
            cur = ch;
        }
        return res + word.length();
    }

}
