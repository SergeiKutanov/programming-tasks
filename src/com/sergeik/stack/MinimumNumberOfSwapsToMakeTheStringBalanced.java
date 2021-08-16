package com.sergeik.stack;

/**
 * You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets
 * '[' and n / 2 closing brackets ']'.
 *
 * A string is called balanced if and only if:
 *
 * It is the empty string, or
 * It can be written as AB, where both A and B are balanced strings, or
 * It can be written as [C], where C is a balanced string.
 * You may swap the brackets at any two indices any number of times.
 *
 * Return the minimum number of swaps to make s balanced.
 */
public class MinimumNumberOfSwapsToMakeTheStringBalanced {

    public static void main(String[] args) {
        assert 2 == solution("]]][[[");
    }

    private static int solution(String s) {
        int balance = 0;
        int res = 0;
        int l = 0, r = s.length() - 1;
        while (l < r) {
            char ch = s.charAt(l);
            if (ch == ']') {
                balance--;
            } else {
                balance++;
            }
            if (balance < 0) {
                res++;
                while (r > 0 && s.charAt(r) != '[')
                    r--;
                balance = 1;
                r--;
            }
            l++;
        }
        return res;
    }

}
