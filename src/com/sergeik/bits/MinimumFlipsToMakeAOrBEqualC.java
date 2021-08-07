package com.sergeik.bits;

/**
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make
 * ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 */
public class MinimumFlipsToMakeAOrBEqualC {

    public static void main(String[] args) {
        assert 3 == solution(2,6,5);
    }

    private static int solution(int a, int b, int c) {
        int res = 0;
        while (c > 0 || a > 0 || b > 0) {
            if ((c & 1) == 0)
                res += (a & 1) + (b & 1);
            else
                res += ((a & 1) == 0 && (b & 1) == 0) ? 1 : 0;
            c >>= 1;
            a >>= 1;
            b >>= 1;
        }
        return res;
    }

}
