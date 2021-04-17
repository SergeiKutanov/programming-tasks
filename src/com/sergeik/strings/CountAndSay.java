package com.sergeik.strings;

public class CountAndSay {

    public static void main(String[] args) {
        int n = 4;
        assert "1211".equals(solution(n));

        n = 10;
        assert "13211311123113112211".equals(solution(n));
    }

    private static String solution(int n) {
        String res = "1";
        for (int i = 2; i <= n; i++) {
            res = pronounce(res);
        }
        return res;
    }

    private static String pronounce(String value) {
        StringBuilder res = new StringBuilder();
        int currentCount = 1;
        char prevChar = value.charAt(0);
        int index = 1;
        while (index < value.length()) {
            if (prevChar == value.charAt(index)) {
                currentCount++;
            } else {
                res.append(Integer.valueOf(currentCount));
                res.append(prevChar);
                currentCount = 1;
                prevChar = value.charAt(index);
            }
            index++;
        }

        if (currentCount > 0) {
            res.append(Integer.valueOf(currentCount));
            res.append(prevChar);
        }

        return res.toString();
    }

}
