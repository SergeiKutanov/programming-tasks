package com.sergeik.bits;

/**
 * Given a non-negative integer num, Return its encoding string.
 *
 * The encoding is done by converting the integer to a string using a secret function that you should deduce
 * from the following table:
 */
public class EncodeNumber {

    public static void main(String[] args) {
        assert "".equals(solution(0));
        assert "0".equals(solution(1));
        assert "1".equals(solution(2));
        assert "10".equals(solution(5));
        assert "000".equals(solution(7));
        assert "1000".equals(solution(23));
        assert "101100".equals(solution(107));
    }

    private static String solution(int num) {
        return Integer.toBinaryString(num + 1).substring(1);
    }

}
