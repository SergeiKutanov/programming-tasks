package com.sergeik.arrays;

/**
 * We are given an array nums of positive integers, and two positive integers left and right (left <= right).
 *
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in
 * that subarray is at least left and at most right.
 */
public class NumberOfSubarraysWithBoundedMaximum {

    public static void main(String[] args) {
        assert 19 == solution(new int[]{732,703,795,420,        //1
                        584,873,662,13,314,988,101,     //4
                        769,646,558,661,808,651,982,878,918,406,551,467,87,139,387,16,531,307,389,939,551,613,36,528,       //7
                        460,404,314,66,111,458,531,944,461,951,419,82,896,467,353,704,905,705,760,61,422,395,298,127,
                        516,153,299,801,341,668,598,98,241},
                            658,
                            719);
        assert 7 == solution(new int[]{2,1,4,3,2,1}, 2,3);
        assert 21 == solution(new int[]{1,1,2,1,2,1,1}, 2,2);
        assert 12 == solution(new int[]{1,1,2,1,1,1}, 2,2);
        assert 3 == solution(new int[]{2,1,4,3}, 2,3);
    }

    private static int solution(int[] nums, int left, int right) {
        return count(nums, right) - count(nums, left - 1);
    }

    private static int count(int[] nums, int bound) {
        int ans = 0, cur = 0;
        for (int n: nums) {
            cur = n <= bound ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }

    private static int dpSolution(int[] nums, int left, int right) {
        int[] dp = new int[nums.length + 1];
        int leftBound = -1;
        int rightBound = 1;
        boolean inArr = false;
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = dp[i - 1];
            if (nums[i - 1] <= right && leftBound == -1)
                leftBound = i;
            if (nums[i - 1] >= left && nums[i - 1] <= right) {
                dp[i] += 1 + i - leftBound;
                rightBound = i;
                inArr = true;
            }
            if (nums[i - 1] < left && inArr) {
                dp[i] += rightBound - leftBound + 1;
            }
            if (nums[i - 1] > right) {
                leftBound = -1;
                inArr = false;
            }
        }
        return dp[nums.length];
    }

}
