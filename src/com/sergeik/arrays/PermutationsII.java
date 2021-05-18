package com.sergeik.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all 
 * possible unique permutations in any order.
 *
 *
 */
public class PermutationsII {

    public static void main(String[] args) {
        solution(new int[]{1,1,2});
    }

    private static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, res, new LinkedList<>(), used);
        return res;
    }

    private static void backtrack(int[] nums, List<List<Integer>> res, List<Integer> cList, boolean[] used) {
        if (cList.size() == nums.length) {
            res.add(new LinkedList<>(cList));
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            cList.add(nums[i]);
            used[i] = true;
            backtrack(nums, res, cList, used);
            cList.remove(cList.size() - 1);
            used[i] = false;
            //skip duplicates
            while (i + 1 < nums.length && nums[i] == nums[i + 1])
                i++;
        }
    }

}
