package com.sergeik.design;

public class RangeSumQueryImmutable {

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        assert 1 == numArray.sumRange(0, 2);
        assert -1 == numArray.sumRange(2, 5);
        assert -3 == numArray.sumRange(0, 5);
    }

    static class NumArray {

        private int[] dp;

        public NumArray(int[] nums) {
            dp = new int[nums.length + 1];
            dp[0] = 0;

            for (int i = 1; i <= nums.length; i++)
                dp[i] = nums[i - 1] + dp[i - 1];
        }

        public int sumRange(int left, int right) {
            return dp[right + 1] - dp[left];
        }
    }

}
