package com.sergeik.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two integer arrays nums1 and nums2. You are tasked to implement a data structure
 * that supports queries of two types:
 *
 * Add a positive integer to an element of a given index in the array nums2.
 * Count the number of pairs (i, j) such that nums1[i] + nums2[j] equals a given value
 * (0 <= i < nums1.length and 0 <= j < nums2.length).
 * Implement the FindSumPairs class:
 *
 * FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
 * void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
 * int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 */
public class FindingPairsWithACertainSum {

    public static void main(String[] args) {
        FindSumPairs findSumPairs = new FindSumPairs(
                new int[] {1, 1, 2, 2, 2, 3},
                new int[] {1, 4, 5, 2, 5, 4});
        assert 8 == findSumPairs.count(7);  // return 8; pairs (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) make 2 + 5 and pairs (5,1), (5,5) make 3 + 4
        findSumPairs.add(3, 2); // now nums2 = [1,4,5,4,5,4]
        assert 2 == findSumPairs.count(8);  // return 2; pairs (5,2), (5,4) make 3 + 5
        assert 1 == findSumPairs.count(4);  // return 1; pair (5,0) makes 3 + 1
        findSumPairs.add(0, 1); // now nums2 = [2,4,5,4,5,4]
        findSumPairs.add(1, 1); // now nums2 = [2,5,5,4,5,4]
        assert 11 == findSumPairs.count(7);  // return 11; pairs (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) make 2 + 5 and pairs (5,3), (5,5) make 3 + 4
    }

    static class FindSumPairs {

        private int[] nums1;
        private int[] nums2;
        private Map<Integer, Integer> map = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            for (int n: nums2)
                map.put(n, map.getOrDefault(n, 0) + 1);
        }

        public void add(int index, int val) {
            int n = nums2[index];
            int count = map.get(n);
            count--;
            if (count == 0) {
                map.remove(n);
            } else {
                map.put(n, count);
            }

            int newVal = n + val;
            nums2[index] = newVal;
            map.put(newVal, map.getOrDefault(newVal, 0) + 1);
        }

        public int count(int tot) {
            int count = 0;
            for (int n: nums1) {
                count += map.getOrDefault(tot - n, 0);
            }
            return count;
        }
    }

}
