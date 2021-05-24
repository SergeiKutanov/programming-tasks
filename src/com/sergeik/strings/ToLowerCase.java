package com.sergeik.strings;

public class ToLowerCase {

    public static void main(String[] args) {
        assert "hello".equals(solution("HelLo"));
    }

    private static String solution(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'A' && ch <= 'Z')
                ch = (char) ('a' + (ch - 'A'));
            sb.append(ch);
        }
        return sb.toString();
    }

}
