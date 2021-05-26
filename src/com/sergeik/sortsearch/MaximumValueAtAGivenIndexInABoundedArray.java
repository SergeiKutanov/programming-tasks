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
        assert 271698267 == solution(3, 0, 815094800);
        assert 7 == solution(5, 0, 28);
        assert 1 == solution(4, 0, 4);
        assert 3 == solution(6, 1, 10);
        assert 2 == solution(4, 2, 6);
    }

    private static int solution(int n, int index, int maxSum) {
        maxSum -= n;
        int min = 0;
        int max = maxSum;
        int middle;
        while (min < max) {
            middle = (max + min + 1) / 2;
            long cSum = getMinSum(n, index, middle);
            if (cSum <= maxSum) {
                min = middle;
            } else if (cSum > maxSum) {
                max = middle - 1;
            }
        }
        return min + 1;
    }

    private static long getMinSum(int n, int index, int a) {
        int leftLength = Math.max(0, a - index);
        long res = (long) (a + leftLength) * (a - leftLength + 1) / 2; //a+....+b == (a+b)*(a-b+1)/2
        int rightLength = Math.max(0, a - (n - index - 1));
        res += (long) (a + rightLength) * (a - rightLength + 1) / 2;
        return res - a;
    }

}
