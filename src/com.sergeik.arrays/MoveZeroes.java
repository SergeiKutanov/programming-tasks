package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        solution(nums);
        assert Arrays.equals(new int[] {1, 3, 12, 0, 0}, nums);

        nums = new int[] {0};
        solution(nums);
        assert Arrays.equals(new int[] {0}, nums);

        nums = new int[] {-5, 0, 0, 5};
        solution(nums);
        assert Arrays.equals(new int[] {-5, 5, 0, 0}, nums);



        nums = new int[] {0, 1, 0, 3, 12};
        twoPointerSolution(nums);
        assert Arrays.equals(new int[] {1, 3, 12, 0, 0}, nums);

        nums = new int[] {0};
        twoPointerSolution(nums);
        assert Arrays.equals(new int[] {0}, nums);

        nums = new int[] {-5, 0, 0, 5};
        twoPointerSolution(nums);
        assert Arrays.equals(new int[] {-5, 5, 0, 0}, nums);
    }

    /**
     * Iterate over the array
     * if > 0 move to next element
     * if 0 find the next > 0 and swap them. If no > 0 left - done
     * @param nums
     */
    private static void solution(int[] nums) {
        int current = 0;
        while (current < nums.length) {
            if (nums[current] != 0) {
                current++;
            } else {
                int nextNonZero = getNextNonZero(nums, current + 1);
                if (nextNonZero != -1) {
                    nums[current] = nums[nextNonZero];
                    nums[nextNonZero] = 0;
                    current++;
                } else {
                    break;
                }
            }
        }
    }

    private static int getNextNonZero(int[] nums, int offset) {
        int res = -1;
        while (offset < nums.length) {
            if (nums[offset] != 0) {
                return offset;
            }
            offset++;
        }
        return res;
    }

    /**
     * Keep reference to next zero element and to next non zero element
     * while zero element index < non zero element
     *  swap them
     *  find next indexes
     *
     * @param nums
     */
    private static void twoPointerSolution(int[] nums) {
        int zeroPointer = findNextZero(nums, 0);
        int nonZeroPointer = findNextNonZero(nums, zeroPointer);

        while (zeroPointer < nonZeroPointer) {
            nums[zeroPointer] = nums[nonZeroPointer];
            nums[nonZeroPointer] = 0;
            zeroPointer = findNextZero(nums, zeroPointer);
            nonZeroPointer = findNextNonZero(nums, nonZeroPointer + 1);
        }

    }

    private static int findNextZero(int[] nums, int offset) {
        int res = Integer.MAX_VALUE;
        while (offset < nums.length) {
            if (nums[offset] == 0) {
                return offset;
            }
            offset++;
        }
        return res;
    }

    private static int findNextNonZero(int[] nums, int offset) {
        int res = Integer.MIN_VALUE;
        while (offset < nums.length) {
            if (nums[offset] != 0) {
                return offset;
            }
            offset++;
        }
        return res;
    }

}
