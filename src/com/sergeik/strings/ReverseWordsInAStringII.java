package com.sergeik.strings;

import java.util.Arrays;

/**
 * Given a character array s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 *
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 */
public class ReverseWordsInAStringII {

    public static void main(String[] args) {
        char[] exp, str;

        exp = new char[] {
                'a'
        };
        str = new char[]{'a'};
        solution(str);
        assert Arrays.equals(
                exp,
                str
        );

        exp = new char[] {
                'b','l','u','e',' ','i','s',' ','s','k','y',' ','t','h','e'
        };
        str = new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        solution(str);
        assert Arrays.equals(
                exp,
                str
        );
    }

    private static void solution(char[] s) {
        int left = 0, right = s.length - 1;
        revers(s, left, right);
        left = 0; right = 1;
        while (right < s.length) {
            while (right < s.length && s[right] != ' ')
                right++;
            revers(s, left, (right == s.length || s[right] == ' ') ? right - 1 : right);
            left = right + 1;
            right += 2;
        }
    }

    private static void  revers(char[] s, int left, int right) {
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++; right--;
        }
    }

    private static void extraMemorySolution(char[] s) {
        char[] copy = Arrays.copyOfRange(s, 0, s.length);
        int left = 0, right = s.length - 1;
        while (left < copy.length) {
            int len = 0;
            while (left + len < copy.length && copy[left + len] != ' ') {
                len++;
                right--;
            }
            if (right >= 0)
                s[right++] = ' ';
            else
                right = 0;
            for (int i = 0; i < len; i++) {
                s[right++] = copy[left++];
            }
            left++;
            right -= len + 2;
        }
    }

}
