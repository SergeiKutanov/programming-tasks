package com.sergeik.strings;

/**
 * Given a palindromic string of lowercase English letters palindrome, replace exactly one character with any
 * lowercase English letter so that the resulting string is not a palindrome and that it is the
 * lexicographically smallest one possible.
 *
 * Return the resulting string. If there is no way to replace a character to make it not a palindrome,
 * return an empty string.
 *
 * A string a is lexicographically smaller than a string b (of the same length) if in the first position
 * where a and b differ, a has a character strictly smaller than the corresponding character in b. For example,
 * "abcc" is lexicographically smaller than "abcd" because the first position they differ is at the fourth
 * character, and 'c' is smaller than 'd'.
 */
public class BreakAPalindrome {

    public static void main(String[] args) {
        assert "aaccba".equals(solution("abccba"));
        assert "".equals(solution("a"));
        assert "ab".equals(solution("aa"));
        assert "abb".equals(solution("aba"));
    }

    private static String solution(String palindrome) {
        char[] str = palindrome.toCharArray();
        for (int i = 0; i < str.length / 2; i++) {
            if (str[i] != 'a') {
                str[i] = 'a';
                return String.valueOf(str);
            }
        }
        str[str.length - 1] = 'b';
        return str.length == 1 ? "" : String.valueOf(str);
    }

}
