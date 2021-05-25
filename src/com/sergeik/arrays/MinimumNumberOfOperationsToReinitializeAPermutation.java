package com.sergeik.arrays;

import java.util.Arrays;

/**
 * You are given an even integer n​​​​​​. You initially have a permutation perm of size n​​
 * where perm[i] == i​ (0-indexed)​​​​.
 *
 * In one operation, you will create a new array arr, and for each i:
 *
 * If i % 2 == 0, then arr[i] = perm[i / 2].
 * If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
 * You will then assign arr​​​​ to perm.
 *
 * Return the minimum non-zero number of operations you need to perform on perm to return
 * the permutation to its initial value.
 */
public class MinimumNumberOfOperationsToReinitializeAPermutation {

    public static void main(String[] args) {
        assert 1 == solutionInversePath(2);
        assert 4 == solutionInversePath(6);
        assert 2 == solutionInversePath(4);

        assert 1 == solutionIndex(2);
        assert 4 == solutionIndex(6);
        assert 2 == solutionIndex(4);

        assert 1 == solution(2);
        assert 4 == solution(6);
        assert 2 == solution(4);
    }

    private static int solutionInversePath(int n) {
        int count = 0;
        int idx = 1;
        while (count == 0 || idx > 1) {
            if (idx < n / 2)
                idx *= 2;
            else
                idx = 2 * idx - n + 1;
            count++;
        }
        return count;
    }

    private static int solutionIndex(int n) {
        int count = 0;
        int idx = 1;
        while (count == 0 || idx > 1) {
            idx = idx * 2 % (n - 1);
            count++;
        }
        return count;
    }

    private static int solution(int n) {
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        String initKey = Arrays.toString(perm);

        int opCount = 0;

        for (int i = 0; i < n; i++) {
            opCount++;
            int arr[] = new int[n];
            for (int j = 0; j < perm.length; j++) {
                if (j % 2 == 0) {
                    arr[j] = perm[j / 2];
                } else {
                    arr[j] = perm[n / 2 + (j - 1) / 2];
                }
            }
            String key = Arrays.toString(arr);
            if (initKey.equals(key))
                return opCount;
            perm = arr;
        }

        return opCount;
    }

}
