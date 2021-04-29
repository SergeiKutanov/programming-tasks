package com.sergeik.bits;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 */
public class ReverseBits {

    public static void main(String[] args) {
        assert 964176192 == solution(43261596);
    }

    public static int solution(int n) {
        int v = 0;
        for (int i = 0; i < 32; i++) {
            int bit = n & 1;
            n = n >> 1;
            v = v << 1;
            v = v | bit;
        }
        return v;
    }

}
