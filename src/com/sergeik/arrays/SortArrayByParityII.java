package com.sergeik.arrays;

/**
 * Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
 *
 * Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
 *
 * Return any answer array that satisfies this condition.
 */
public class SortArrayByParityII {

    public static void main(String[] args) {
        solution(new int[]{4,2,5,7});
    }

    private static int[] solution(int[] nums) {
        int evenIndex = 0;
        int oddIndex = 1;
        while (true) {
            while (evenIndex < nums.length && nums[evenIndex] % 2 == 0 )
                evenIndex += 2;
            while (oddIndex < nums.length && nums[oddIndex] % 2 != 0)
                oddIndex += 2;
            if (evenIndex < nums.length && oddIndex < nums.length) {
                int tmp = nums[oddIndex];
                nums[oddIndex] = nums[evenIndex];
                nums[evenIndex] = tmp;
            } else {
                return nums;
            }
        }
    }

}
