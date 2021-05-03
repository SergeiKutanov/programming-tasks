package com.sergeik.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        assert 3 == solution(new int[]{1,2,0,1});
        assert 4 == solution(new int[]{100,4,200,1,3,2});
        assert 9 == solution(new int[]{0,3,7,2,5,8,4,6,0,1});
        assert 0 == solution(new int[]{});
    }

    private static int solution(int[] nums) {
        Set<Integer> nSet = new HashSet<>();
        for (int n: nums) {
            nSet.add(n);
        }
        int maxS = 0;
        for (int n: nums) {
            if (!nSet.contains(n-1)) {
                int count = n + 1;
                while (nSet.contains(count))
                    count++;
                maxS = Math.max(count - n, maxS);
            }
        }
        return maxS;
    }

}
