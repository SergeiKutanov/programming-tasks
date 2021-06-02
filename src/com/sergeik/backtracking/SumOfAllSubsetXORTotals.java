package com.sergeik.backtracking;

import java.util.LinkedList;
import java.util.List;

public class SumOfAllSubsetXORTotals {

    private static int total = 0;

    public static void main(String[] args) {
        assert 28 == solution(new int[] {5,1,6});
    }

    private static int solution(int[] nums) {
        total = 0;
        createPowerSet(new LinkedList<>(), nums, 0);
        return total;
    }

    private static void  createPowerSet(List<Integer> cList, int[] nums, int start) {
        int xor = 0;
        for (int n: cList) {
            xor ^= n;
        }
        total += xor;
        for (int i = start; i < nums.length; i++) {
            cList.add(nums[i]);
            createPowerSet(cList, nums, i + 1);
            cList.remove(cList.size() - 1);
        }
    }

}
