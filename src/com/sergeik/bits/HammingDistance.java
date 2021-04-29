package com.sergeik.bits;

/**
 * The Hamming distance between two integers is the number of positions
 * at which the corresponding bits are different.
 *
 * Given two integers x and y, return the Hamming distance between them.
 */
public class HammingDistance {

    public static void main(String[] args) {
        assert 2 == solution(1, 4);
        assert 1 == solution(3, 1);

        assert 2 == brianKerninghanAlg(1, 4);
        assert 1 == brianKerninghanAlg(3, 1);
    }

    private static int solution(int x, int y) {
        int diff = 0;
        int diffBits = x ^ y;
        for (int i = 0; i < 32; i++) {
            diff += diffBits & 1;
            diffBits = diffBits >> 1;
        }
        return diff;
    }

    private static int brianKerninghanAlg(int x, int y) {
        int count = 0;
        int xor = x ^ y;
        while (xor != 0) {
            count++;
            xor = xor & (xor - 1);
        }
        return count;
    }

}
