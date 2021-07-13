package com.sergeik.sortsearch;

import java.util.*;

/**
 * You have n packages that you are trying to place in boxes, one package in each box. There are m suppliers that
 * each produce boxes of different sizes (with infinite supply). A package can be placed in a box if the size
 * of the package is less than or equal to the size of the box.
 *
 * The package sizes are given as an integer array packages, where packages[i] is the size of the ith package.
 * The suppliers are given as a 2D integer array boxes, where boxes[j] is an array of box sizes that the jth
 * supplier produces.
 *
 * You want to choose a single supplier and use boxes from them such that the total wasted space is minimized.
 * For each package in a box, we define the space wasted to be size of the box - size of the package. The total
 * wasted space is the sum of the space wasted in all the boxes.
 *
 * For example, if you have to fit packages with sizes [2,3,5] and the supplier offers boxes of sizes [4,8],
 * you can fit the packages of size-2 and size-3 into two boxes of size-4 and the package with size-5 into a box
 * of size-8. This would result in a waste of (4-2) + (4-3) + (8-5) = 6.
 * Return the minimum total wasted space by choosing the box supplier optimally, or -1 if it is impossible to fit
 * all the packages inside boxes. Since the answer may be large, return it modulo 109 + 7.
 */
public class MinimumSpaceWastedFromPackaging {

    public static void main(String[] args) {
        assert 359991 == solution(new int[] {1,1,1,1,1,1,1,1,1}, new int[][]{{40000}});
        assert 9 == solution(new int[] {3,5,8,10,11,12}, new int[][]{
                {12},
                {11,9},
                {10,5,14}
        });
    }

    private static int solution(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        long inf = Long.MAX_VALUE, res = inf, mod = 1000000007, sumA = 0L;
        for (int a : packages)
            sumA += a;
        for (int[] B : boxes) {
            Arrays.sort(B);
            if (B[B.length - 1] < packages[packages.length - 1]) continue;
            long cur = 0, i = 0, j;
            for (int b : B) {
                j = binarySearch(packages, b + 1);
                cur += b * (j - i);
                i = j;
            }
            res = Math.min(res, cur);
        }
        return res < inf ? (int)((res - sumA) % mod) : -1;
    }

    private static int binarySearch(int[] A, int b) {
        int l = 0, r = A.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (A[m] < b)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

}
