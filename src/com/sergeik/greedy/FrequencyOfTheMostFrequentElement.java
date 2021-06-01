package com.sergeik.greedy;

import java.util.Arrays;

/**
 * The frequency of an element is the number of times it occurs in an array.
 *
 * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums
 * and increment the element at that index by 1.
 *
 * Return the maximum possible frequency of an element after performing at most k operations.
 */
public class FrequencyOfTheMostFrequentElement {

    public static void main(String[] args) {
        assert 1 == slidingWindowSolution(new int[]{3,9,6}, 2);
        assert 2 == slidingWindowSolution(new int[]{1,4,8,13}, 5);
        assert 3 == slidingWindowSolution(new int[]{1,2,4}, 5);
    }

    private static int slidingWindowSolution(int[] nums, int k) {
        /*
                 Number of operations needed for all elements in the window [startIndex, endIndex] to hit A[endIndex]
                 Example:
                 Consider arr with [1, 2, 3, 4] with startIndex = 0; endIndex = 3: i.e If 1, 2, 3 wants to become 4.
                 Number of operations needed
                 = (4-1)+(4-2)+(4-3)+(4-4) = 6.
                 =  4 + 4 + 4 + 4 - (1 + 2 + 3+ 4)
                 = 4 * 4 - (1 + 2 + 3 + 4)
                 = (number of elements) * ElementToReach - sum of elements in the window
         */
        Arrays.sort(nums);
        int res = 0;
        int leftBound = 0;
        long sum = 0;
        for (int rightBound = 0; rightBound < nums.length; rightBound++) {
            sum += nums[rightBound];
            while ((rightBound - leftBound + 1) * nums[rightBound] - sum > k) {
                sum -= nums[leftBound];
                leftBound++;
            }
            res = Math.max(res, (rightBound - leftBound + 1));
        }
        return res;
    }

    private static int bruteForceSolution(int[] nums, int k) {
        Arrays.sort(nums);
        int max = 0;
        int r = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            int n = nums[i];
            int a = k;
            int idx = i - 1;
            int freq = 1;
            while (a > 0 && idx >= 0) {
                int diff = n - nums[idx];
                if (diff <= a) {
                    a -= diff;
                    freq++;
                } else {
                    a = 0;
                }
                idx--;
                max = Math.max(freq, max);
            }
        }
        return max;
    }

}
