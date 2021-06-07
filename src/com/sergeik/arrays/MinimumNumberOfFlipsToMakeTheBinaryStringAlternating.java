package com.sergeik.arrays;

/**
 * You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
 *
 * Type-1: Remove the character at the start of the string s and append it to the end of the string.
 * Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
 * Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
 *
 * The string is called alternating if no two adjacent characters are equal.
 *
 * For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 */
public class MinimumNumberOfFlipsToMakeTheBinaryStringAlternating {

    public static void main(String[] args) {
        assert 5 == slidingWindowSolution("10001100101000000");
        assert 0 == slidingWindowSolution("011");
        assert 2 == slidingWindowSolution("01001001101");
        assert 4 == slidingWindowSolution("001000000010");
        assert 2 == slidingWindowSolution("111000");
        assert 0 == slidingWindowSolution("010");
        assert 1 == slidingWindowSolution("1110");

        assert 5 == solution("10001100101000000");
        assert 0 == solution("011");
        assert 2 == solution("01001001101");
        assert 4 == solution("001000000010");
        assert 2 == solution("111000");
        assert 0 == solution("010");
        assert 1 == solution("1110");
    }

    private static int slidingWindowSolution(String s) {
        int len = s.length();
        String doubleS = s + s;
        StringBuilder s0 = new StringBuilder(), s1 = new StringBuilder();
        for (int i = 0; i < doubleS.length(); i++) {
            s0.append(i % 2 == 0 ? '0' : '1');
            s1.append(i % 2 == 0 ? '1' : '0');
        }
        int zeroFirst = 0;
        int oneFirst = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < doubleS.length(); i++) {
            if (s0.charAt(i) != doubleS.charAt(i))
                zeroFirst++;
            if (s1.charAt(i) != doubleS.charAt(i))
                oneFirst++;
            if (i >= len) {
                if (s0.charAt(i - len) != doubleS.charAt(i - len))
                    zeroFirst--;
                if (s1.charAt(i - len) != doubleS.charAt(i - len))
                    oneFirst--;
            }
            if (i >= len - 1) {
                res = Math.min(zeroFirst, res);
                res = Math.min(oneFirst, res);
            }
        }
        return res;
    }

    private static int solution(String s) {
        int[] ops = new int[2];
        countMinOp(s, ops);
        int res = Math.min(ops[0], ops[1]);
        if (s.length() % 2 == 0)
            return res;

        for (int i = 1; i < s.length(); i++) {
            int tmp = ops[0];
            ops[0] = ops[1];
            ops[1] = tmp;
            if (s.charAt(s.length() - i) == '0') {
                ops[0] = Math.max(0, ops[0] - 1);
                ops[1]++;
            } else {
                ops[0]++;
                ops[1] = Math.max(0, ops[1] - 1);
            }
            res = Math.min(res, ops[0]);
            res = Math.min(res, ops[1]);
        }
        return res;
    }

    private static void countMinOp(String s, int[] ops) {
        int op1 = 0, op2 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                op1 += s.charAt(i) == '0' ? 0 : 1;
                op2 += s.charAt(i) == '1' ? 0 : 1;
            } else {
                op1 += s.charAt(i) == '1' ? 0 : 1;
                op2 += s.charAt(i) == '0' ? 0 : 1;
            }
        }
        ops[0] = op1;
        ops[1] = op2;
    }

    private static int bruteSolution(String s) {
        int res = countMinOp(s);
        for (int i = 0; i < s.length() - 1; i++) {
            s = s.substring(1) + s.charAt(0);
            res = Math.min(
                    res,
                    countMinOp(s)
            );
        }
        return res;
    }

    private static int countMinOp(String s) {
        int op1 = 0, op2 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                op1 += s.charAt(i) == '0' ? 0 : 1;
                op2 += s.charAt(i) == '1' ? 0 : 1;
            } else {
                op1 += s.charAt(i) == '1' ? 0 : 1;
                op2 += s.charAt(i) == '0' ? 0 : 1;
            }
        }
        System.out.println(s);
        System.out.println(op1 + ":" + op2);
        return Math.min(op1, op2);
    }

}
