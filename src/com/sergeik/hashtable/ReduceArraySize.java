package com.sergeik.hashtable;

import java.util.*;

/**
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 *
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 */
public class ReduceArraySize {

    public static void main(String[] args) {
        assert 2 == solution(new int[] {3,3,3,3,5,5,5,2,2,7});
    }

    private static int solution(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: arr)
            map.put(n, map.getOrDefault(n, 0) + 1);

        Integer[] values = map.values().toArray(new Integer[0]);
        Arrays.sort(values, (a,b) -> b - a);
        int count = 0, idx = 0;
        int len = arr.length / 2;
        while (count < len) {
            count += values[idx++];
        }
        return idx;
    }

}
