package com.sergeik.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class SubsetsII {

    public static void main(String[] args) {
        solution(new int[]{1,2,2});
    }

    private static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        createPowerSet(res, new LinkedList<>(), nums, 0);
        return res;
    }

    private static void createPowerSet(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start) {
        res.add(new LinkedList<>(tmp));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;
            tmp.add(nums[i]);
            createPowerSet(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

}
