package com.sergeik.recursion;

import java.util.Arrays;

public class ReverseString {

    public static void main(String[] args) {
        char[] w = new char[]{'h', 'e', 'l', 'l', 'o'};
        solution(w);
        assert Arrays.equals(
                new char[] {'o', 'l', 'l', 'e', 'h'},
                w
        );
    }

    private static void solution(char[] str) {
        int s = 0;
        int e = str.length - 1;
        while (s < e) {
            char tmp = str[s];
            str[s] = str[e];
            str[e] = tmp;
            s++;
            e--;
        }
    }



}
