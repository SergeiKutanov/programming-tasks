package com.sergeik.strings;

public class AddStrings {

    public static void main(String[] args) {
        assert "134".equals(solution("11", "123"));
        assert "533".equals(solution("456", "77"));
        assert "0".equals(solution("0", "0"));
        assert "10".equals(solution("1", "9"));
    }

    private static String solution(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n1 = num1.length() - 1, n2 = num2.length() - 1;
        int carryOver = 0;
        for (;n1 >= 0 && n2 >= 0; n1--, n2--) {
            int val = num1.charAt(n1) - '0' + num2.charAt(n2) - '0' + carryOver;
            if (val >= 10) {
                carryOver = 1;
                val %= 10;
            } else
                carryOver = 0;
            sb.insert(0, (char) (val + '0'));
        }
        while (n1 >= 0) {
            int val = num1.charAt(n1) - '0' + carryOver;
            if (val >= 10) {
                carryOver = 1;
                val %= 10;
            } else
                carryOver = 0;
            sb.insert(0, (char) (val + '0'));
            n1--;
        }
        while (n2 >= 0) {
            int val = num2.charAt(n2) - '0' + carryOver;
            if (val >= 10) {
                carryOver = 1;
                val %= 10;
            } else
                carryOver = 0;
            sb.insert(0, (char) (val + '0'));
            n2--;
        }
        if (carryOver == 1)
            sb.insert(0, '1');
        return sb.toString();
    }

}
