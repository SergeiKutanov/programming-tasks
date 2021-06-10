package com.sergeik.dynamic;

import java.util.Arrays;

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i]
 * is the number of 1's in the binary representation of i.
 */
public class CountingBits {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1},
                dpSolution(16)
        );
        assert Arrays.equals(
                new int[] {0,1,1,2,1,2},
                dpSolution(5)
        );

        assert Arrays.equals(
                new int[] {0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1},
                bitSolution(16)
        );
        assert Arrays.equals(
                new int[] {0,1,1,2,1,2},
                bitSolution(5)
        );
    }

    private static int[] bitSolution(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++)
            res[i] = res[i >> 1] + (i & 1);
        return res;
    }

    private static int[] dpSolution(int n) {
        int res[] = new int[n + 1];
        if (n < 1)
            return res;
        res[1] = 1;
        for (int i = 2; i <= n; i *= 2) {
            res[i] = 1;
            for (int j = 1; j < i && i + j <= n; j++) {
                res[i + j] = 1 + res[j];
            }
        }
        return res;
    }

}
