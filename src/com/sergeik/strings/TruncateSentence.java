package com.sergeik.strings;

public class TruncateSentence {

    public static void main(String[] args) {
        assert "Hello how are you".equals(solution("Hello how are you Contestant", 4));
    }

    private static String solution(String s, int k) {
        int index = 0;
        int spacesCount = 0;
        while (index < s.length()) {
            if (s.charAt(index) == ' ') {
                spacesCount++;
                if (spacesCount == k)
                    return s.substring(0, index);
            }
            index++;
        }
        return s;
    }

}
