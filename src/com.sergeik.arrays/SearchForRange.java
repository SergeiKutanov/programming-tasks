package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, 
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class SearchForRange {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{1,1},
                solution(new int[] {1,4}, 4)
        );
        assert Arrays.equals(
                new int[]{0,0},
                solution(new int[] {1}, 1)
        );
        assert Arrays.equals(
                new int[]{3,4},
                solution(new int[] {5,7,7,8,8,10}, 8)
        );
        assert Arrays.equals(
                new int[]{-1,-1},
                solution(new int[] {5,7,7,8,8,10}, 6)
        );
        assert Arrays.equals(
                new int[]{-1,-1},
                solution(new int[] {}, 0)
        );
    }

    /**
     * 1 2 3 4 5 6
     * @param nums
     * @param target
     * @return
     */
    private static int[] solution(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        if (nums.length == 0) {
            return res;
        }
        int left = 0;
        int right = nums.length - 1;
        int middle = -1;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                break;
            }
        }
        if (middle >= 0 && nums[middle] != target) {
            return res;
        }

        int low = middle;
        int high = middle;
        while (low > 0  && nums[low - 1] == target) {
            low--;
        }
        while (high < nums.length - 1 && nums[high + 1] == target) {
            high++;
        }

        res[0] = low;
        res[1] = high;
        return res;
    }

}
