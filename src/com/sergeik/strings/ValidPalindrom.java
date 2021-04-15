package com.sergeik.strings;

public class ValidPalindrom {

    public static void main(String[] args) {
        assert solution("A man, a plan, a canal: Panama");
        assert !solution("race a car");
        assert solution("");
        assert solution(" ");
        assert solution("a");
        assert !solution("0P");
    }

    /**
     * Time O(n)
     * Memory O(n)
     * @param s
     * @return
     */
    private static boolean solution(String s) {
        int startIndex = 0;
        int endIndex = s.length() - 1;

        while (startIndex < endIndex) {
            char startChar = Character.toLowerCase(s.charAt(startIndex));
            char endChar = Character.toLowerCase(s.charAt(endIndex));

            if (!isComparable(startChar)) {
                startIndex++;
            } else if (!isComparable(endChar)) {
                endIndex--;
            } else if (startChar == endChar) {
                startIndex++;
                endIndex--;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isComparable(char ch) {
        return (ch >= 'a' && ch <= 'z') ||
                (ch >= '0' && ch <= '9');
    }

}
