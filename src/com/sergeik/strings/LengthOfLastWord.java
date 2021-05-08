package com.sergeik.strings;

/**
 * Given a string s consists of some words separated by spaces, return the length of the last word in the string.
 * If the last word does not exist, return 0.
 *
 * A word is a maximal substring consisting of non-space characters only.
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        assert 5 == solution("Hello world");
        assert 0 == solution(" ");
    }

    private static int solution(String s) {
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch != ' ')
                count++;
            else if (ch == ' ' && count > 0)
                break;;
        }
        return count;
    }

}
