package com.sergeik.sortsearch;

/**
 * You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed)
 * that satisfies the following conditions:
 *
 * nums.length == n
 * nums[i] is a positive integer where 0 <= i < n.
 * abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * The sum of all the elements of nums does not exceed maxSum.
 * nums[index] is maximized.
 * Return nums[index] of the constructed array.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 */
public class MaximumValueAtAGivenIndexInABoundedArray {

    public static void main(String[] args) {
        assert 3 == solution(6, 1, 10);
        assert 2 == solution(4, 2, 6);
    }


    private static int solution(int n, int index, int maxSum) {
        maxSum -= n;
        int left = 0;
        int right = maxSum;
        int middle;
        while (left < right) {
            middle = (left + right + 1) / 2;
            if (verify(n, index, middle) <= maxSum)
                left = middle;
            else
                right = middle - 1;
        }
        return left + 1;
    }

    private static long verify(int n, int index, int a) {
        int b = Math.max(a - index, 0);
        long res = (long) (a + b) * (a - b + 1) / 2;
        b = Math.max(a - ((n - 1) - index), 0);
        res += (long) (a + b) * (a - b + 1) / 2;
        return res - a;
    }

}
