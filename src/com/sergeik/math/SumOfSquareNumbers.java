package com.sergeik.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 */
public class SumOfSquareNumbers {

    public static void main(String[] args) {
        assert !solution(3);
        assert solution(4);
        assert solution(2);
        assert solution(1);
        assert solution(5);
    }

    private static boolean solution(int c) {
        int left, thresh = (int) Math.sqrt(c), right;
        for (int i = 0; i <= thresh; i++) {
            int num = i;
            left = i; right = thresh;
            while (left <= right) {
                int mid = (left + right) / 2;
                int sum = num * num + mid * mid;
                if (sum < c) {
                    left = mid + 1;
                } else if (sum > c) {
                    right = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean mapSolution(int c) {
        int thresh = (int) Math.sqrt(c);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= thresh; i++) {
            map.put(i * i, i);
        }
        for (int i: map.keySet()) {
            int diff = c - i;
            if (map.containsKey(diff))
                return true;
        }
        return false;
    }

}
