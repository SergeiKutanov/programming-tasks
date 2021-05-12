package com.sergeik.backtracking;

import java.util.*;

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 */
public class Permutations {

    public static void main(String[] args) {
        solution(new int[]{1,2,3});
    }

    private static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        backtrack(res, new LinkedList<>(), nums);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, List<Integer> tmpList, int[] nums) {
        if (tmpList.size() == nums.length) {
            res.add(new LinkedList<>(tmpList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                //better to use O(1) lookup structure
                if (tmpList.contains(nums[i]))
                    continue;
                tmpList.add(nums[i]);
                backtrack(res, tmpList, nums);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }


}
