package com.sergeik.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d)
 * such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.
 *
 *
 */
public class TupleWithSameProduct {

    public static void main(String[] args) {
        assert 40 == solution(new int[]{2,3,4,6,8,12});
    }

    private static int solution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                res += 8 * map.getOrDefault(product, 0);
                map.put(product, map.getOrDefault(product, 0) + 1);
            }
        }
        return res;
    }

}
