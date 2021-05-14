package com.sergeik.arrays;

import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {1,2},
                solution(new int[]{1,1,1,2,2,3}, 2)
        );
    }

    private static int[] solution(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n: nums)
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> pQueue = new PriorityQueue<Map.Entry<Integer, Integer>>(
                (a, b) -> b.getValue() - a.getValue()
        );
        for (Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
            pQueue.offer(entry);
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = pQueue.poll().getKey();

        return res;
    }

}
