package com.sergeik.backtracking;

import java.util.LinkedList;
import java.util.List;

public class Combinations {

    public static void main(String[] args) {
        solution(4, 2);
    }

    private static List<List<Integer>> solution(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        backtrack(res, new LinkedList<>(), 1, k, n);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, List<Integer> cList, int start, int k, int n) {
        if (0 == k) {
            res.add(new LinkedList<>(cList));
            return;
        }

        for (int i = start; i <= n; i++) {
            cList.add(i);
            backtrack(res, cList, i + 1, k - 1, n);
            cList.remove(cList.size() - 1);
        }
    }

}
