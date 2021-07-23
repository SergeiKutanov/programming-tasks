package com.sergeik.strings;

public class RemoveAllOccurencesOfASubstring {

    public static void main(String[] args) {
        assert "dab".endsWith(solution("daabcbaabcbc", "abc"));
    }

    private static String solution(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        int idx;
        while ((idx = sb.indexOf(part)) != -1) {
            sb.replace(idx, idx + part.length(), "");
        }
        return sb.toString();
    }

}
