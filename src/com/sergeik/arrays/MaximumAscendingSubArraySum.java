package com.sergeik.arrays;

public class MaximumAscendingSubArraySum {

    public static void main(String[] args) {
        assert 33 == solution(new int[]{12,17,15,13,10,11,12});
        assert 100 == solution(new int[]{100,10,1});
        assert 150 == solution(new int[]{10,20,30,40,50});
        assert 65 == solution(new int[]{10,20,30,5,10,50});
    }

    private static int solution(int[] nums) {
        if (nums.length == 0)
            return 0;
        int maxSum = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                currentSum += nums[i];
            else {
                maxSum = Math.max(maxSum, currentSum);
                currentSum = nums[i];
            }
        }
        return Math.max(maxSum, currentSum);
    }

}
