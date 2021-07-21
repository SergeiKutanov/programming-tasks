package com.sergeik.strings;

public class DecryptStringFromAlphabetToIntegerMapping {

    public static void main(String[] args) {
        assert "jkab".equals(solution("10#11#12"));
        assert "acz".equals(solution("1326#"));
        assert "abcdefghijklmnopqrstuvwxyz".equals(solution("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));
    }

    private static String solution(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                res.append((char)('a' + (ch - 49)));
            } else if (ch == '#') {
                int offset = Integer.parseInt(s.substring(i - 2, i)) - 1;
                res.setLength(res.length() - 2);
                res.append((char)('a' + offset));
            }
        }
        return res.toString();
    }

}
