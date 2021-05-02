package com.sergeik.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 */
public class FourSumTwo {

    public static void main(String[] args) {

        assert 17 == solution(
                new int[]{0,1,-1},
                new int[]{-1,1,0},
                new int[]{0,0,1},
                new int[]{-1,1,1}
        );

        assert 6 == solution(
                new int[]{-1,-1},
                new int[]{-1,1},
                new int[]{-1,1},
                new int[]{1,-1}
        );

        assert 2 == solution(
                new int[] {1,2},
                new int[] {-2,-1},
                new int[] {-1,2},
                new int[] {0,2}
        );
    }

    private static int solution(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> firstSums = new HashMap<>();
        for (int n1: nums1) {
            for (int n2: nums2) {
                firstSums.put(n1 + n2, firstSums.getOrDefault(n1 + n2, 0) + 1);
            }
        }

        int counter = 0;
        for (int n3: nums3) {
            for (int n4: nums4) {
                counter += firstSums.getOrDefault(-1 * (n3 + n4), 0);
            }
        }
        return counter;
    }

    private static int bruteSolution(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        int counter = 0;

        for (int n1: nums1) {
            for (int n2: nums2) {
                for (int n3: nums3) {
                    for (int n4: nums4) {
                        if ((n1 + n2 + n3 + n4) == 0)
                            counter++;
                    }
                }
            }
        }

        return counter;
    }

}
