package com.sergeik.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class Subsets {

    public static void main(String[] args) {
        solution(new int[]{1,2,3});
    }

    private static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        createPowerSet(result, new LinkedList<>(), nums, 0);
        return result;
    }

    private static void createPowerSet(List<List<Integer>> result, LinkedList<Integer> tmp, int[] nums, int start) {
        result.add(new LinkedList<>(tmp));
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            createPowerSet(result, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }


}
