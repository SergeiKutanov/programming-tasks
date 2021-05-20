package com.sergeik.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Given an array of integers arr, write a function that returns true if and only if the number of occurrences
 * of each value in the array is unique.
 *
 *
 */
public class UniqueNumberOfOccurences {

    public static void main(String[] args) {
        assert !solution(new int[]{1,2});
    }

    private static boolean solution(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        return map.size() == new HashSet<>(map.values()).size();
    }

}
