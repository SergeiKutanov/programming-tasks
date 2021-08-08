package com.sergeik.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class Subsets {

    public static void main(String[] args) {
        bitMaskStringSolution(new int[]{1,2,3});
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

    private static List<List<Integer>> bitMaskStringSolution(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1 << nums.length; i < 1 << nums.length + 1; i++) {
            List<Integer> subset = new ArrayList<>();
            String mask = Integer.toBinaryString(i).substring(1);
            for (int j = 0; j < mask.length(); j++) {
                if (mask.charAt(j) == '1')
                    subset.add(nums[j]);
            }
            res.add(subset);
        }
        return res;
    }

    private static List<List<Integer>> bitMaskSolution(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 1 << nums.length; i++) {
            List<Integer> subset = new ArrayList<>();
            int mask = i;
            int idx = 0;
            while (mask > 0) {
                if ((mask & 1) == 1)
                    subset.add(nums[idx]);
                idx++;
                mask >>= 1;
            }
            res.add(subset);
        }
        return res;
    }


}
