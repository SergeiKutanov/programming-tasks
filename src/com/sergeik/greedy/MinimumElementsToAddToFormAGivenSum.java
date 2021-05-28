package com.sergeik.greedy;

/**
 * You are given an integer array nums and two integers limit and goal. The array nums has an interesting property
 * that abs(nums[i]) <= limit.
 *
 * Return the minimum number of elements you need to add to make the sum of the array equal to goal. The array must
 * maintain its property that abs(nums[i]) <= limit.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 */
public class MinimumElementsToAddToFormAGivenSum {

    public static void main(String[] args) {

        int[] largeArray = new int[100000];
        for (int i = 0; i < largeArray.length; i++)
            largeArray[i] = 1000000;

        assert 101000 == solution(largeArray, 1000000, -1000000000);
        assert 2 == solution(new int[]{1,-1,1}, 3, -4);
        assert 1 == solution(new int[]{1,-10,9,1}, 100, 0);
    }

    private static int solution(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int n: nums)
            sum += n;
        long diff = Math.abs(goal - sum);
        long limitNumbers = diff / limit;
        if (diff % limit > 0)
            limitNumbers++;
        return (int) limitNumbers;
    }

}
