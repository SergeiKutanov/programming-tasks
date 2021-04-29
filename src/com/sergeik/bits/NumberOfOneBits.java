package com.sergeik.bits;

/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has
 * (also known as the Hamming weight).
 *
 * Note:
 *
 * Note that in some languages, such as Java, there is no unsigned integer type. In this case,
 * the input will be given as a signed integer type. It should not affect your implementation,
 * as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore,
 * in Example 3, the input represents the signed integer. -3.
 */
public class NumberOfOneBits {

    public static void main(String[] args) {
        int n = Integer.valueOf("00000000000000000000000000001011", 2);
        assert 3 == solution(n);
        assert 1 == solution(Integer.valueOf("00000000000000000000000010000000", 2));
        assert 30 == solution(Integer.valueOf("1111111111111111111111111111101", 2));
    }

    private static int solution(int n) {
        int counter = 0;
        for (int i = 0; i < 32; i++) {
            counter += n & 1;
            n = n >> 1;
        }
        return counter;
    }
}
