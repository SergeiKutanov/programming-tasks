package com.sergeik.math;

import java.util.Arrays;

/**
 * You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts
 * such that all of these parts represent the same binary value.
 *
 * If it is possible, return any [i, j] with i + 1 < j, such that:
 *
 * arr[0], arr[1], ..., arr[i] is the first part,
 * arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
 * arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
 * All three parts have equal binary values.
 * If it is not possible, return [-1, -1].
 *
 * Note that the entire part is used when considering what binary value it represents. For example, [1,1,0]
 * represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 */
public class ThreeEqualParts {

    private static Integer[][] memo;

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {3,5}, solution(new int[]{0,0,0,1,1,1}));
        assert Arrays.equals(new int[] {1,4}, solution(new int[]{0,1,0,1,1}));
        assert Arrays.equals(new int[] {0,2}, solution(new int[] {1,1,0,0,1}));
        assert Arrays.equals(new int[] {-1,-1}, solution(new int[] {1,1,0,1,1}));
        assert Arrays.equals(new int[] {0,3}, solution(new int[] {1,0,1,0,1}));
    }

    private static int[] solution(int[] arr) {
        int[] res = new int[]{-1,-1};
        int ones = 0;
        for (int n: arr)
            ones += n;

        if (ones % 3 != 0) //impossible, number of ones should be divisible by three
            return res;

        int onesInSection = ones / 3;

        if (onesInSection == 0)
            return new int[]{0, arr.length - 1};

        int i1 = -1, j1 = -1, i2 = -1, j2 = -1, i3 = -1, j3 = -1;
        int su = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 1) {
                su += 1;
                if (su == 1) i1 = i;
                if (su == onesInSection) j1 = i;
                if (su == onesInSection + 1) i2 = i;
                if (su == 2 * onesInSection) j2 = i;
                if (su == 2 * onesInSection + 1) i3 = i;
                if (su == 3 * onesInSection) j3 = i;
            }
        }

        // The array is in the form W [i1, j1] X [i2, j2] Y [i3, j3] Z
        // where [i1, j1] is a block of 1s, etc.
        int[] part1 = Arrays.copyOfRange(arr, i1, j1 + 1);
        int[] part2 = Arrays.copyOfRange(arr, i2, j2 + 1);
        int[] part3 = Arrays.copyOfRange(arr, i3, j3 + 1);

        if (!Arrays.equals(part1, part2)) return res;
        if (!Arrays.equals(part1, part3)) return res;

        // x, y, z: the number of zeros after part 1, 2, 3
        int x = i2 - j1 - 1;
        int y = i3 - j2 - 1;
        int z = arr.length - j3 - 1;

        if (x < z || y < z) return res;
        return new int[]{j1 + z, j2 + z + 1};
    }

    private static int[] tleSolution(int[] arr) {
        int[] res = new int[] {-1,-1};
        memo = new Integer[arr.length + 1][arr.length + 1];
        for (int i = 1; i <= arr.length - 2; i++) {
            int n = binToInt(arr, 0, i);
            boolean found = dfs(arr, i, 2, res, n);
            if (found) {
                res[0] = i - 1;
                return res;
            }
        }

        return res;
    }

    private static boolean dfs(int[] arr, int start, int k, int[] res, int n) {
        if (k == 0)
            return start == arr.length;
        for (int i = start + 1; i <= arr.length - k + 1; i++) {
            int number = binToInt(arr, start, i);
            if (number == n) {
                boolean found = dfs(arr, i, k - 1, res, n);
                if (found) {
                    res[1] = i;
                    return true;
                }
            } else if (number > n)
                return false;
        }
        return false;
    }

    private static int binToInt(int[] arr, int start, int end) {
        if (memo[start][end] != null)
            return memo[start][end];
        int n = 0;
        for (int i = start; i < end; i++) {
            n <<= 1;
            n += arr[i];
        }
        memo[start][end] = n;
        return n;
    }

}
