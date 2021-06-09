package com.sergeik.hashtable;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {

    public static void main(String[] args) {
        assert 2 == solution(
                new int[] {1,2},
                new int[] {-2,-1},
                new int[] {-1,2},
                new int[] {0,2}
        );
    }

    private static int solution(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n1: nums1)
            for (int n2: nums2)
                map.put(n1 + n2, map.getOrDefault(n1 + n2, 0) + 1);

        int count = 0;

        for (int n3: nums3)
            for (int n4: nums4) {
                int sumToLookFor = -(n3 + n4);
                count += map.getOrDefault(sumToLookFor, 0);
            }

        return count;
    }

}
