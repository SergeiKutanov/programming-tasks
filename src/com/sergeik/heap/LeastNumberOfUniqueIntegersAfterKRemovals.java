package com.sergeik.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing
 * exactly k elements.
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {5,5,4}, 1);
        assert 2 == solution(new int[] {4,3,1,1,3,3,2}, 3);
    }

    private static int solution(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n: arr)
            count.put(n, count.getOrDefault(n, 0) + 1);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int val: count.values())
            heap.offer(val);
        while (k-- > 0 && !heap.isEmpty()) {
            int val = heap.poll();
            if (--val > 0)
                heap.offer(val);
        }
        return heap.size();
    }

}
