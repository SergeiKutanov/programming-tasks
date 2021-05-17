package com.sergeik.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 */
public class CombinationSumII {

    public static void main(String[] args) {
        List<List<Integer>> res = solution(
                new int[] {10,1,2,7,6,1,5},
                8
        );
        String foo = "";
    }

    private static List<List<Integer>> solution(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(res, new LinkedList<>(), candidates, target, 0);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, LinkedList<Integer> cList, int[] candidates, int target, int start) {
        if (target < 0)
            return;
        if (target == 0) {
            res.add(new LinkedList<>(cList));
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1])
                    continue;
                cList.add(candidates[i]);
                backtrack(res, cList, candidates, target - candidates[i], i + 1);
                cList.remove(cList.size() - 1);
            }
        }
    }

}
