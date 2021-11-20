package com.sergeik.sortsearch;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for
 * one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 */
public class SingleElementInASortedArray {

    public static void main(String[] args) {
        assert 10 == solution(new int[] {3,3,7,7,10,11,11});
        assert 12 == solution(new int[] {3,3,7,7,11,11,12});
        assert 0 == solution(new int[] {0,3,3,7,7,11,11});
        assert 2 == solution(new int[] {1,1,2,3,3,4,4,8,8});
    }

    private static int solution(int[] nums) {
        int len = nums.length;
        int low = 0, high = len - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            //0 0 1 1 2 2 3 4 4
            //0 0 1 2 2 3 3
            if (
                    ((mid > 0 && nums[mid - 1] != nums[mid] || mid == 0)) &&
                    ((mid < len - 1 && nums[mid + 1] != nums[mid]) || mid == len - 1)
            ) {
                return nums[mid];
            }
            int idx = mid - (mid % 2);
            if (nums[idx] == nums[idx + 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
