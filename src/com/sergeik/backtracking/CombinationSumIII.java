package com.sergeik.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the
 * combinations may be returned in any order.
 */
public class CombinationSumIII {

    public static void main(String[] args) {
        List<List<Integer>> exp, res;
        exp = Arrays.asList(
                Arrays.asList(1,2,6),
                Arrays.asList(1,3,5),
                Arrays.asList(2,3,4)
        );
        res = solution(3, 9);
        for (int i = 0; i < exp.size(); i++) {
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));
        }
    }

    private static List<List<Integer>> solution(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(k, n, 0, 1, res, new ArrayList<>());
        return res;
    }

    private static void backtrack(int k, int n, int sum, int start, List<List<Integer>> res, List<Integer> cRes) {
        if (sum > n)
            return;
        if (cRes.size() == k) {
            if (sum == n)
                res.add(new ArrayList<>(cRes));
            return;
        }
        for (int i = start; i <= Math.min(9, n - sum); i++) {
            cRes.add(i);
            backtrack(k, n, sum + i, i + 1, res, cRes);
            cRes.remove(cRes.size() - 1);
        }
    }

}
