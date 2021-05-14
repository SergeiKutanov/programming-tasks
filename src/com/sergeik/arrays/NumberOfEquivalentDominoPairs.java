package com.sergeik.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either
 * (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.
 *
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is
 * equivalent to dominoes[j].
 */
public class NumberOfEquivalentDominoPairs {

    public static void main(String[] args) {
        assert 3 == solution(new int[][]{
                {1,2},
                {1,2},
                {1,1},
                {1,2},
                {2,2}
        });
        assert 2 == solution(new int[][]{
                {1,2},
                {2,1},
                {1,4},
                {5,6},
                {6,5},
                {1,1}
        });
        assert 1 == solution(new int[][]{
                {1,2},
                {2,1},
                {3,4},
                {5,6}
        });
    }

    private static int solution(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] d: dominoes) {
            int v = Math.min(d[0], d[1]) * 10 + Math.max(d[0], d[1]);
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        int res = 0;
        for (int v: map.values()) {
            res += v * (v - 1) / 2;
        }
        return res;
    }

    private static int bruteSolution(int[][] dominoes) {
        int pairs = 0;
        boolean[] seen = new boolean[dominoes.length];
        for (int i = 0; i < dominoes.length - 1; i++) {
            seen[i] = true;
            for (int j = i + 1; j < dominoes.length; j++) {
                if (isEqual(dominoes[i], dominoes[j])) {
                    seen[j] = true;
                    pairs++;
                }
            }
        }
        return pairs;
    }

    private static boolean isEqual(int[] d1, int[] d2) {
        if (d1[0] == d2[0] && d1[1] == d2[1])
            return true;
        if (d1[0] == d2[1] && d1[1] == d2[0])
            return true;
        return false;
    }

}
