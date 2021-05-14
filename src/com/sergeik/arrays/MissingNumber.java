package com.sergeik.arrays;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */
public class MissingNumber {

    public static void main(String[] args) {
        assert 2 == solution(new int[]{0,1});
        assert 2 == solution(new int[]{3,0,1});
    }

    /**
     * Sum of consecutive numbers [1,n] = n * (n+1) / 2
     * @param nums
     * @return
     */
    private static int solution(int[] nums) {
        int maxSum = nums.length * (nums.length + 1) / 2;
        for (int n: nums) {
            maxSum -= n;
        }
        return maxSum;
    }

}
