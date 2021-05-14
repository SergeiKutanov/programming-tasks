package com.sergeik.arrays;

import java.util.*;

/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum
 * frequency of any one of its elements.
 *
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 */
public class DegreeOfAnArray {

    public static void main(String[] args) {
        assert 1 == solution(new int[]{1});
        assert 6 == solution(new int[]{1,2,2,3,1,4,2});
        assert 2 == solution(new int[]{1,2,2,3,1});
    }

    private static int solution(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            } else {
                map.get(nums[i]).add(i);
            }
            max = Math.max(max, map.get(nums[i]).size());
        }
        int minLength = Integer.MAX_VALUE;
        for (List<Integer> list: map.values()) {
            if (list.size() == max) {
                minLength = Math.min(list.get(list.size() - 1) - list.get(0), minLength);
            }
        }
        return ++minLength;
    }

    private static int solutionWithArrays(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{1, i, i});
            } else {
                int[] data = map.get(nums[i]);
                data[0]++;
                data[2] = i;
                if (max < data[0])
                    max = data[0];
            }
        }
        int min = Integer.MAX_VALUE;
        for (int[] data: map.values()) {
            if (data[0] == max) {
                if (data[2] - data[1] < min)
                    min = data[2] - data[1];
            }
        }
        return min + 1;
    }

}
