package com.sergeik.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 *
 * Given an integer n representing the total number of bits in the code, return any sequence of gray code.
 *
 * A gray code sequence must begin with 0.
 */
public class GreyCode {

    public static void main(String[] args) {
        List<Integer> res = solution(2);
        res = solution(4);
    }

    /**
     * Unsigned int to grey code (num >> 1) ^ num;
     * reverse operation
     * for (mask = num >> 1; mask != 0; mask = mask >> 1)
     *     {
     *         num = num ^ mask;
     *     }
     *
     * @param n
     * @return
     */
    private static List<Integer> solution(int n) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }

}
