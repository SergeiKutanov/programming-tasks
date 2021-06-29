package com.sergeik.arrays;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array
 * if you can flip at most k 0's.
 *
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {

        assert 13 == slidingWindow(new int[] {0,0,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,1,1,0,0,0,0,1,1,1}, 3);
        assert 4 == slidingWindow(new int[] {0,0,0,1}, 4);
        assert 10 == slidingWindow(new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3);
        assert 6 == slidingWindow(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2);

        assert 4 == solution(new int[] {0,0,0,1}, 4);
        assert 10 == solution(new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3);
        assert 6 == solution(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2);
    }

    private static int slidingWindow(int[] nums, int k) {
        int left = 0, right;
        for (right = 0; right < nums.length; right++) {
            if (nums[right] == 0)
                k--;
            if (k < 0) {
                if (nums[left] == 0)
                    k++;
                left++;
            }
        }
        return right - left;
    }

    private static int solution(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (i > 0 && i < nums.length - 1) {
                    if (nums[i - 1] == 1 && nums[i + 1] == 1)
                        continue;
                }
                int leftMax = expand(nums, i, k, -1);
                int rightMax = expand(nums, i, k, 1);
                res = Math.max(res, Math.max(leftMax, rightMax));
            }
        }
        return res;
    }

    private static int expand(int[] nums, int i, int k, int dir) {
        int counter = 1;
        while (i >= 0 && i < nums.length) {
            i += dir;
            if (i >= 0 && i < nums.length) {
                if (nums[i] == 0 && k > 0) {
                    k--;
                    counter++;
                } else if (nums[i] == 1)
                    counter++;
                else
                    break;
            }
        }
        return counter;
    }

}
