package com.sergeik.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 */
public class ContiguousArray {

    public static void main(String[] args) {
        assert 6 == solution(new int[] {0,0,1,0,0,0,1,1});
        assert 2 == solution(new int[] {0,1});
        assert 2 == solution(new int[] {0,1,0});
        assert 10 == solution(new int[] {0,0,1,1,0,1,1,1,0,0});
    }

    private static int solution(int[] nums) {
        int[] preSum = new int[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int res = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
            if (!map.containsKey(preSum[i]))
                map.put(preSum[i], i);
            else {
                res = Math.max(res, i - map.get(preSum[i]));
            }
        }
        return res;
    }

}
