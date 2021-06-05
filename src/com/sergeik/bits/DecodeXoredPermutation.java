package com.sergeik.bits;

import java.util.Arrays;

/**
 * There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.
 *
 * It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1].
 * For example, if perm = [1,3,2], then encoded = [2,1].
 *
 * Given the encoded array, return the original array perm. It is guaranteed that the answer exists and is unique.
 *
 *
 */
public class DecodeXoredPermutation {

    public static void main(String[] args) {
        assert Arrays.equals(new int[]{2,4,1,5,3}, solution(new int[] {6,5,4,6}));
    }

    private static int[] solution(int[] encoded) {
        int[] res = new int[encoded.length + 1];
        int m = (encoded.length + 1) % 4;
        if (m == 0)
            res[0] = 0;
        else if (m == 1)
            res[0] = 1;
        else if (m == 2)
            res[0] = encoded.length + 1;
        else if (m == 3)
            res[0] = 0;
//        for (int i = 1; i <= encoded.length + 1; i++) {
//            res[0] ^= i;
//        }

        for (int i = 1; i < encoded.length; i += 2)
            res[0] ^= encoded[i];
        for (int i = 1; i < res.length; i++) {
            res[i] = encoded[i - 1] ^ res[i - 1];
        }
        return res;
    }

}
