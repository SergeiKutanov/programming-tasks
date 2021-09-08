package com.sergeik.bits;

/**
 * Given an integer n, you must transform it into 0 using the following operations any number of times:
 *
 * Change the rightmost (0th) bit in the binary representation of n.
 * Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1 and the (i-2)th through
 * 0th bits are set to 0.
 * Return the minimum number of operations to transform n into 0.
 */
public class MinimumOneBitOperationsToMakeIntegersZero {

    public static void main(String[] args) {
        assert 14 == solution(9);
    }

    private static int solution(int n) {
        return minimumOneBitOperations(n, 0);
    }

    private static int minimumOneBitOperations(int n, int res) {
        if (n == 0) return res;
        int b = 1;
        while ((b << 1) <= n)
            b = b << 1;
        return minimumOneBitOperations((b >> 1) ^ b ^ n, res + b);
    }
}

