package com.sergeik.arrays;

import java.util.*;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 */
public class FourSum {

    private static int length = 0;

    public static void main(String[] args) {
        List<List<Integer>> res = solution(new int[]{1,0,-1,0,-2,2}, 0);
        solution(new int[]{2,2,2,2,2}, 8);
    }

    private static List<List<Integer>> solution(int[] nums, int target) {
        Arrays.sort(nums);
        length = nums.length;
        return kSum(nums, target, 4, 0);
    }

    private static List<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        List<List<Integer>> res = new LinkedList<>();
        if (length <= index)
            return res;

        if (k == 2) {
            int i = index;
            int j = length - 1;
            while (i < j) {
                if (target - nums[i] == nums[j]) {
                    List<Integer> tmp = new LinkedList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    res.add(tmp);
                    while (i < j && nums[i] == nums[i + 1]) i++;
                    while (i < j && nums[j] == nums[j - 1]) j--;
                    i++;
                    j--;
                } else if (target - nums[i] > nums[j])
                    i++;
                else
                    j--;
            }
        } else {
            for (int i = index; i < length - k + 2; i++) {
                List<List<Integer>> tmp = kSum(nums, target - nums[i], k - 1, i + 1);
                if (tmp != null && tmp.size() > 0) {
                    for (List<Integer> l: tmp)
                        l.add(nums[i]);
                }
                res.addAll(tmp);
                while (i < length - 1 && nums[i] == nums[i + 1])
                    i++;
            }
        }

        return res;
    }

}
