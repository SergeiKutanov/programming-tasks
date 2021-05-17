package com.sergeik.math;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2,
 * also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        assert "6".equals(solution("2", "3"));
        assert "0".equals(solution("9133", "0"));
        assert "891".equals(solution("9", "99"));
        assert "81".equals(solution("9", "9"));
        assert "56088".equals(solution("123", "456"));
    }

    private static String solution(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int[] pos = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int res = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int pos1 = i + j;
                int pos2 = pos1 + 1;
                int sum = pos[pos2] + res;
                pos[pos1] += sum / 10;
                pos[pos2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p: pos) {
            if (p == 0 && sb.length() == 0)
                continue;
            sb.append(p);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    private static String solutionTall(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0"))
            return "0";

        StringBuilder result = new StringBuilder();
        int carryOver = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            StringBuilder lRes = new StringBuilder();
            int tens = num2.length() - i - 1;
            while (tens-- > 0) {
                lRes.insert(0, "0");
            }
            for (int j = num1.length() - 1; j >= 0; j--) {
                int res = (num2.charAt(i) - '0') * (num1.charAt(j) - '0') + carryOver;
                lRes.insert(0, res % 10);
                carryOver = res / 10;
            }
            if (carryOver > 0) {
                lRes.insert(0, carryOver);
                carryOver = 0;
            }

            result = plus(result, lRes);
        }

        if (carryOver > 0) {
            result.insert(0, carryOver);
        }

        return result.toString();
    }

    private static StringBuilder plus(StringBuilder str1, StringBuilder str2) {
        int first = str1.length() - 1;
        int second = str2.length() - 1;
        int carryOver = 0;
        StringBuilder res = new StringBuilder();
        while (first >= 0 && second >= 0) {
            int iRes = carryOver + (str1.charAt(first) - '0') + (str2.charAt(second) - '0');
            res.insert(0, iRes % 10);
            carryOver = iRes / 10;
            first--;
            second--;
        }

        while (first >= 0) {
            int iRes = (str1.charAt(first) - '0') + carryOver;
            res.insert(0, iRes % 10);
            carryOver = iRes / 10;
            first--;
        }
        while (second >= 0) {
            int iRes = (str2.charAt(second) - '0') + carryOver;
            res.insert(0, iRes % 10);
            carryOver = iRes / 10;
            second--;
        }
        if (carryOver > 0)
            res.insert(0, carryOver);
        return res;
    }

}
