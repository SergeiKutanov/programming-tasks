package com.sergeik.strings;

import java.util.Arrays;
import java.util.Collections;

public class CustomSortStrings {

    public static void main(String[] args) {
        assert "cbad".equals(solution("cba", "abcd"));
    }

    private static String solution(String order, String str) {
        int[] orderChars = new int[26];
        for (int i = 0; i < orderChars.length; i++) {
            orderChars[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < order.length(); i++) {
            orderChars[order.charAt(i) - 'a'] = i;
        }

        Character[] chars = new Character[str.length()];
        for (int i = 0; i < str.length(); i++)
            chars[i] = str.charAt(i);

        Arrays.sort(chars, (a,b) -> {
            return orderChars[a - 'a'] - orderChars[b - 'a'];
        });

        StringBuilder sb = new StringBuilder();
        for (char ch: chars) {
            sb.append(ch);
        }
        return sb.toString();
    }

}
