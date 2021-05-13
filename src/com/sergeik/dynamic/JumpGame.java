package com.sergeik.dynamic;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 */
public class JumpGame {

    public static void main(String[] args) {
        assert solution(new int[] {2,3,1,1,4});
        assert !solution(new int[] {3,2,1,0,4});
    }

    private static boolean solution(int[] nums) {
        int maxDest = 0;
        int curPosition = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxDest = Math.max(maxDest, i + nums[i]);
            if (i == curPosition) {
                curPosition = maxDest;
            }
        }
        return curPosition >= nums.length - 1;
    }

}
