package com.sergeik.strings;

public class IntegerToRoman {

    private static String roman = "IVXLCDM";

    public static void main(String[] args) {
        assert "".equals(solution(0));
        assert "MCMXCIV".equals(solution(1994));
        assert "III".equals(solution(3));
        assert "IV".equals(solution(4));
    }

    private static String solution(int num) {
        StringBuilder result = new StringBuilder();
        int offset = 0;
        while (num > 0) {
            int digit = num % 10;
            getChar(digit, offset, result);
            num /= 10;
            offset += 2;
        }
        return result.toString();
    }

    private static void getChar(int digit, int offset, StringBuilder sb) {
        if (digit == 10) {
            sb.insert(0, roman.charAt(offset + 2));
        } else if (digit == 9) {
            sb.insert(0, roman.charAt(offset + 2));
            sb.insert(0, roman.charAt(offset));
        } else if (digit < 9 && digit > 4) {
            sb.insert(0, roman.charAt(offset + 1));
            while (digit-- > 5)
                sb.insert(1, roman.charAt(offset));
        } else if (digit == 4) {
            sb.insert(0, roman.charAt(offset + 1));
            sb.insert(0, roman.charAt(offset));
        } else {
            while (digit-- > 0) {
                sb.insert(0, roman.charAt(offset));
            }
        }

    }

}
