package com.sergeik.arrays;

import java.util.*;

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique
 * combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique
 * if the frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations
 * for the given input.
 */
public class CombinationSum {

    public static void main(String[] args) {
        List<List<Integer>> res = solution(
                new int[] {2,3,6,7},
                7
        );
        String foo = "";
    }

    private static List<List<Integer>> solution(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(result, new LinkedList<>(), candidates, target, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> currentList, int[] candidates, int remain, int start) {
        if (remain < 0)
            return;
        if (remain == 0)
            result.add(new LinkedList<>(currentList));
        else {
            for (int i = start; i < candidates.length; i++) {
                currentList.add(candidates[i]);
                backtrack(result, currentList, candidates, remain - candidates[i], i);
                currentList.remove(currentList.size() - 1);
            }
        }
    }

}
