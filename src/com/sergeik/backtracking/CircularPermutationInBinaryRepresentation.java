package com.sergeik.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
 *
 * p[0] = start
 * p[i] and p[i+1] differ by only one bit in their binary representation.
 * p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
 */
public class CircularPermutationInBinaryRepresentation {

    public static void main(String[] args) {
        assert Arrays.equals(new Integer[] {3,2,0,1}, solution(2, 3).toArray());
    }

    private static List<Integer> itSolution(int n, int start) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ (i >> 1));
        }
        while (res.get(0) != start) {
            res.add(res.get(0));
            res.remove(0);
        }
        return res;
    }

    private static List<Integer> solution(int n, int start) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++)
            res.add(start ^ i ^ i >> 1);
        return res;
    }

}
