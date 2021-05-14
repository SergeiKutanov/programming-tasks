package com.sergeik.arrays;

import java.util.*;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays
 * and you may return the result in any order.
 */
public class IntersectionOfTwoArrays {

    public static void main(String[] args) {

        int[] nums1 = new int[] {1, 2, 2, 1};
        int[] nums2 = new int[] {2, 2};
        assert Arrays.equals(solution(nums1, nums2), new int[] {2, 2});
        assert Arrays.equals(hashMapSolution(nums1, nums2), new int[] {2, 2});

        nums1 = new int[] {4, 9, 5};
        nums2 = new int[] {9, 4, 9, 8, 4};
        assert Arrays.equals(solution(nums1, nums2), new int[] {4, 9});
        assert Arrays.equals(hashMapSolution(nums1, nums2), new int[] {4, 9});

    }

    /**
     * Iterate over all items keeping track of used indexes from num2.
     * The approach takes O(n*m) time and O(m) memory where n - size of num1, m - size of num2
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] solution(int[] nums1, int[] nums2) {
        Set<Integer> takenIndexes = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();

        //place smaller array in the outer loop
        int[] smaller = new int[]{};
        int[] bigger = new int[] {};
        if (nums1.length <= nums2.length) {
            smaller = nums1;
            bigger = nums2;
        } else {
            smaller = nums2;
            bigger = nums1;
        }

        for (int i = 0; i < smaller.length; i++) {
            int valIn1 = smaller[i];
            for (int j = 0; j < bigger.length; j++) {
                int valIn2 = bigger[j];
                if (valIn1 == valIn2 && !takenIndexes.contains(j)) {
                    duplicates.add(valIn1);
                    takenIndexes.add(j); //keep track of used indexes
                    break;  // essential to break here so valIn1 is not counted twice
                }
            }
        }
        return listToArr(duplicates);
    }

    /**
     * Count duplicates first. So duplicate maps looks like valueFromArray -> numberOfOccurences
     * Then iterate over the over array decreasing the counters
     *
     * Running time - O(n+m)
     * Memory O(m)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] hashMapSolution(int[] nums1, int[] nums2) {
        List<Integer> duplicates = new ArrayList<>();
        Map<Integer, Integer> num2Counters = countDuplicates(nums2);
        for (int i: nums1) {
            int counter = num2Counters.getOrDefault(i, 0);
            if (counter > 0) {
                counter--;
                duplicates.add(i);
                if (counter == 0) {
                    num2Counters.remove(i);
                } else {
                    num2Counters.put(i, counter);
                }
            }
        }
        return listToArr(duplicates);
    }

    private static Map<Integer, Integer> countDuplicates(int[] nums) {
        Map<Integer, Integer> duplicatesMap = new HashMap<>();
        for (int i: nums) {
            duplicatesMap.put(i, duplicatesMap.getOrDefault(i, 0) + 1);
        }
        return duplicatesMap;
    }

    private static int[] listToArr(List<Integer> list) {
        int[] r = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            r[i] = list.get(i);
        }
        return r;
    }

}
