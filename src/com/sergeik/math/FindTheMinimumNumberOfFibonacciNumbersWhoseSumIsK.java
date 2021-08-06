package com.sergeik.math;

import java.util.*;

/**
 * Given an integer k, return the minimum number of Fibonacci numbers whose sum is equal to k. The same Fibonacci
 * number can be used multiple times.
 *
 * The Fibonacci numbers are defined as:
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 for n > 2.
 * It is guaranteed that for the given constraints we can always find such Fibonacci numbers that sum up to k.
 */
public class FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK {

    public static void main(String[] args) {
        assert 2 == solution(7);
        assert 2 == solution(10);
        assert 3 == solution(19);
    }

    private static int solution(int k){
        List<Integer> fibs = new ArrayList<>();
        fibs.add(1);
        fibs.add(1);
        while (fibs.get(fibs.size() - 1) <= k) {
            fibs.add(fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2));
        }
        fibs.remove(fibs.size() - 1);
        int res = 0;
        for (int i = fibs.size() - 1; i >= 0; i--) {
            if (fibs.get(i) <= k) {
                k -= fibs.get(i);
                res++;
            }
            if (k == 0)
                break;
        }
        return res;
    }

}
