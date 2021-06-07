package com.sergeik.greedy;

/**
 * You are given a binary string binary consisting of only 0's or 1's. You can apply each of the following
 * operations any number of times:
 *
 * Operation 1: If the number contains the substring "00", you can replace it with "10".
 * For example, "00010" -> "10010"
 * Operation 2: If the number contains the substring "10", you can replace it with "01".
 * For example, "00010" -> "00001"
 * Return the maximum binary string you can obtain after any number of operations. Binary string x is greater
 * than binary string y if x's decimal representation is greater than y's decimal representation.
 */
public class MaximumBinaryStringAfterChange {

    public static void main(String[] args) {
        assert "1110".equals(solution("1100"));
        assert "0".equals(solution("0"));
        assert "11101".equals(solution("10001"));
        assert "01".equals(solution("01"));
        assert "111011".equals(solution("000110"));
    }

    private static String solution(String binary) {
        int zeros = 0, ones = 0, len = binary.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++)
            res.append('1');

        for (int i = 0; i < len; i++)
            if (binary.charAt(i) == '0')
                zeros++;
            else if (zeros == 0)
                ones++;

        if (ones < len)
            res.setCharAt(ones + zeros - 1, '0');

        return res.toString();
    }

}
