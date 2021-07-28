package com.sergeik.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * For some fixed n, an array nums is beautiful if it is a permutation of the integers 1, 2, ..., n, such that:
 *
 * For every i < j, there is no k with i < k < j such that nums[k] * 2 = nums[i] + nums[j].
 *
 * Given n, return any beautiful array nums.  (It is guaranteed that one exists.)
 */
public class BeautifulArray {

    private static Map<Integer, int[]> memo;

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {1,9,5,3,7,2,6,4,8}, solution(9));
        assert Arrays.equals(new int[] {1,9,5,3,7,2,6,4,8}, dAndQSolution(9));
    }

    private static int[] dAndQSolution(int n) {
        memo = new HashMap<>();
        return dfs(n);
    }

    private static int[] dfs(int n) {
        if (memo.containsKey(n))
            return memo.get(n);
        int[] res = new int[n];
        if (n == 1) {
            res[0] = 1;
        } else {
            int t = 0;
            for (int x: dfs((n + 1) / 2)) //odds
                res[t++] = 2 * x - 1;
            for (int x: dfs(n / 2)) //evens
                res[t++] = 2 * x;
        }
        memo.put(n, res);
        return res;
    }

    private static int[] solution(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        while (res.size() < n) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i: res)
                if (i * 2 - 1 <= n)
                    tmp.add(i * 2 - 1);
            for (int i: res)
                if (i * 2 <= n)
                    tmp.add(i * 2);
            res = tmp;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

}
