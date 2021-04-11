package com.sergeik.strings;

import java.util.Arrays;

/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] s = new char[] {'h', 'e', 'l', 'l', 'o'};
        solution(s);
        assert Arrays.equals(s, new char[] {'o', 'l', 'l', 'e', 'h'});

        s = new char[] {'H', 'a', 'n', 'n', 'a', 'h'};
        solution(s);
        assert Arrays.equals(s, new char[] {'h', 'a', 'n', 'n', 'a', 'H'});
    }

    /**
     * Time O(n)
     * Memory O(1)
     * @param s
     *
     * Another possible solution is to use stack, bu this will require additional memory.
     */
    private static void solution(char[] s) {
        int firstRunner = 0;
        int lastRunner = s.length - 1;

        while (firstRunner < lastRunner) {
            char tmp = s[firstRunner];
            s[firstRunner] = s[lastRunner];
            s[lastRunner] = tmp;
            firstRunner++;
            lastRunner--;
        }
    }

}
