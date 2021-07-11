package com.sergeik.sortsearch;

import java.util.*;

/**
 * Given an array of integers nums, return the largest integer that only occurs once.
 *
 * If no integer occurs once, return -1.
 */
public class LargestUniqueNumber {

    public static void main(String[] args) {
        assert 8 == arraySolution(new int[] {5,7,3,9,4,9,8,3,1});
        assert -1 == solution(new int[] {1,1,2,2});
    }

    private static int solution(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == 0 || nums[i] != nums[i - 1])
                return nums[i];
            while (i > 0 && nums[i] == nums[i - 1])
                i--;
        }
        return -1;
    }

    private static int hashMapSolution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        int res = -1;
        for (int key: map.keySet()) {
            if (map.get(key) == 1)
                res = Math.max(res, key);
        }
        return res;
    }

    private static int arraySolution(int[] nums) {
        int[] freq = new int[1001];
        for (int n: nums)
            freq[n]++;
        int res = -1;
        for (int key = 0; key < freq.length; key++) {
            if (freq[key] == 1)
                res = Math.max(res, key);
        }
        return res;
    }

}
