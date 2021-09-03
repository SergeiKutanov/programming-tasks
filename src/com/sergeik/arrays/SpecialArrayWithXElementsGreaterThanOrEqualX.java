package com.sergeik.arrays;

import java.util.Arrays;

public class SpecialArrayWithXElementsGreaterThanOrEqualX {

    public static void main(String[] args) {
        assert 2 == solution(new int[] {3,5});
        assert 3 == solution(new int[] {0,4,3,0,4});
        assert -1 == solution(new int[]{3,6,7,7,0});
    }

    private static int solution(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int n = nums.length - i;
            if (n <= nums[i] && (i == 0 || nums[i - 1] < n))
                return n;
        }
        return -1;
    }

    private static int binarySearchSolution(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length; i++) {
            int idx = Arrays.binarySearch(nums, i);
            if (idx < 0)
                idx = -(idx + 1);
            while (idx > 0 && nums[idx - 1] == i)
                idx--;
            if (nums.length - idx == i)
                return i;
        }
        return -1;
    }

}
