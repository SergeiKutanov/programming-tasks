package com.sergeik.arrays;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 */
public class JumpGameTwo {

    public static void main(String[] args) {
        assert 3 == solution(new int[]{5,9,3,2,1,0,2,3,3,1,0,0});
        assert 2 == solution(new int[]{1,2,3});
        assert 1 == solution(new int[]{2,1});
        assert 2 == solution(new int[]{10,9,8,7,6,5,4,3,2,1,1,0});
        assert 2 == solution(new int[]{2,3,0,1,4});
        assert 2 == solution(new int[]{2,3,1,1,4});
    }

    private static int solution(int[] nums) {
        int nHops = 0;
        int maxDest = 0;
        int curDest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxDest = Math.max(maxDest, i + nums[i]);
            if (i == curDest) {
                curDest = maxDest;
                nHops++;
            }
        }
        return nHops;
    }

}
