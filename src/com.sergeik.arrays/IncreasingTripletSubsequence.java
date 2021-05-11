package com.sergeik.arrays;

import java.util.Arrays;

public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        assert solution(new int[]{20,100,10,12,5,13});
        assert solution(new int[]{2,1,5,0,4,6});
        assert solution(new int[]{1,2,3,4,5});
        assert !solution(new int[]{5,4,3,2,1});

        assert dpSolution(new int[]{20,100,10,12,5,13});
        assert dpSolution(new int[]{2,1,5,0,4,6});
        assert dpSolution(new int[]{1,2,3,4,5});
        assert !dpSolution(new int[]{5,4,3,2,1});

        assert !quickestSolution(new int[]{1,1,1,1,1});
        assert quickestSolution(new int[]{20,100,10,12,5,13});
        assert quickestSolution(new int[]{2,1,5,0,4,6});
        assert quickestSolution(new int[]{1,2,3,4,5});
        assert !quickestSolution(new int[]{5,4,3,2,1});
    }

    private static boolean quickestSolution(int[] nums) {
        int min = Integer.MAX_VALUE;
        int middle = Integer.MAX_VALUE;
        for (int n: nums) {
            if (n <= min) {
                min = n;
            } else if (n <= middle) {
                middle = n;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Idea - find three digit smaller than current
     * @param nums
     * @return
     */
    private static boolean dpSolution(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 0);
        int max = -1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
            if (max >= 2)
                return true;
        }
        return max >= 2;
    }

    private static boolean solution(int[] nums) {
        for (int middle = 1; middle < nums.length - 1; middle++) {
            if (extend(middle, nums))
                return true;
        }
        return false;
    }

    private static boolean extend(int middle, int[] nums) {
        int start = middle;
        int end = middle;

        while (start >= 0 && nums[start] >= nums[middle]) {
            start--;
        }
        while (end < nums.length && nums[middle] >= nums[end]) {
            end++;
        }

        if (start < 0 || end > nums.length - 1)
            return false;

        return nums[start] < nums[middle] && nums[middle] < nums[end];
    }

}
