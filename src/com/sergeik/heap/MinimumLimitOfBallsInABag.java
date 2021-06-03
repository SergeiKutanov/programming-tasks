package com.sergeik.heap;

/**
 * You are given an integer array nums where the ith bag contains nums[i] balls. You are also given
 * an integer maxOperations.
 *
 * You can perform the following operation at most maxOperations times:
 *
 * Take any bag of balls and divide it into two new bags with a positive number of balls.
 * For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
 * Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
 *
 * Return the minimum possible penalty after performing the operations.
 */
public class MinimumLimitOfBallsInABag {

    public static void main(String[] args) {
        assert 7 == solution(new int[]{7, 17}, 2);
        assert 3 == solution(new int[]{9}, 2);
        assert 2 == solution(new int[]{2,4,8,2}, 4);
    }

    private static int solution(int[] nums, int maxOperations) {
        int left = 1;
        int right = 1_000_000_000;
        while (left < right) {
            int middle = (left + right) / 2;
            int count = 0;
            for (int n: nums) {
                count += (n - 1) / middle;
            }
            if (count > maxOperations) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

}
