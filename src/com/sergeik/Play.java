package com.sergeik;


import com.sergeik.trees.TreeNode;

import java.util.*;

public class Play {

    public static void main(String[] args) {
        List<List<Integer>> exp, res;

        res = solution(new int[] {1,0,-1,0,-2,2}, 0);
        exp = new LinkedList<>();
        exp.add(Arrays.asList(1,2,-1,-2));
        exp.add(Arrays.asList(0,2,0,-2));
        exp.add(Arrays.asList(0,1,0,-1));
        for (int i = 0; i < exp.size(); i++) {
            for (int j = 0; j < exp.get(i).size(); j++) {
                assert exp.get(i).get(j).equals(res.get(i).get(j));
            }
        }
    }

    private static List<List<Integer>> solution(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }

    private static List<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        List<List<Integer>> res = new LinkedList<>();
        if (index >= nums.length)
            return res;
        if (k == 2) {
            int i = index;
            int j = nums.length - 1;
            while (i < j) {
                int diff = target - nums[i];
                if (diff == nums[j]) {
                    List<Integer> tmp = new LinkedList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    res.add(tmp);
                    while (i < j && nums[i] == nums[i + 1]) i++;
                    while (i < j && nums[j] == nums[j - 1]) j--;
                    i++; j--;
                } else if (diff > nums[j])
                    i++;
                else
                    j--;
            }
        } else {
            for (int i = index; i < nums.length - k + 2; i++) {
                List<List<Integer>> tmp = kSum(nums, target - nums[i], k - 1, i + 1);
                if (tmp.size() > 0) {
                    for (List<Integer> l: tmp)
                        l.add(nums[i]);
                }
                res.addAll(tmp);
                while (i < nums.length - 1 && nums[i] == nums[i + 1])
                    i++;
            }
        }
        return res;
    }

}
