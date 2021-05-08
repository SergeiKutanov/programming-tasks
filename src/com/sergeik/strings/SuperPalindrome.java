package com.sergeik.strings;

/**
 * Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.
 *
 * Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].
 *
 */
public class SuperPalindrome {

    public static void main(String[] args) {
        assert 2 == solution("40000000000000000", "50000000000000000");
        assert 4 == solution("4", "1000");
        assert 1 == solution("1", "2");
    }

    private static int solution(String left, String right) {
        long start = Long.valueOf(left);
        long end = Long.valueOf(right);

        int magic = 100000;
        int answer = 0;

        //odd
        for (int k = 0; k < magic; k++) {
            StringBuilder sb = new StringBuilder(Integer.toString(k));
            for (int i = sb.length() - 2; i >= 0; i--)
                sb.append(sb.charAt(i));
            long v = Long.valueOf(sb.toString());
            v *= v;
            if (v > end)
                break;
            if (v >= start && isPalindrome(v))
                answer++;
        }

        //even
        for (int k = 0; k < magic; k++) {
            StringBuilder sb = new StringBuilder(Integer.toString(k));
            for (int i = sb.length() - 1; i >= 0; i--)
                sb.append(sb.charAt(i));
            long v = Long.valueOf(sb.toString());
            v *= v;
            if (v > end)
                break;
            if (v >= start && isPalindrome(v))
                answer++;
        }

        return answer;
    }

    private static boolean isPalindrome(long v) {
        return v == reverse(v);
    }

    private static long reverse(long v) {
        long res = 0;
        while (v > 0) {
            res = res * 10 + v % 10;
            v /= 10;
        }
        return res;
    }

}
