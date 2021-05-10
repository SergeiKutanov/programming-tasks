package com.sergeik.arrays;

import java.util.*;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */
public class ThreeSum {

    public static void main(String[] args) {
        List<List<Integer>> res = solution(new int[]{-1,0,1,2,-1,-4});
        assert res.size() == 2;
        assert Arrays.asList(-1,-1,2).equals(res.get(0));
        assert Arrays.asList(-1,0,1).equals(res.get(1));
    }

    private static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int j = i + 1;
            int k = nums.length - 1;
            int target = -nums[i];
            while (j < k) {
                int twoSum = nums[j] + nums[k];
                if (twoSum == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else if (twoSum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }
}
