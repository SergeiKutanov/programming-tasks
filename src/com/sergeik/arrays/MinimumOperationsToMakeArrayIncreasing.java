package com.sergeik.arrays;

public class MinimumOperationsToMakeArrayIncreasing {

    public static void main(String[] args) {
        assert 3 == solution(new int[]{1,1,1});
        assert 14 == solution(new int[]{1,5,2,4,1});
        assert 0 == solution(new int[]{8});
    }

    private static int solution(int[] nums) {
        if (nums.length <= 1)
            return 0;
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] <= nums[i]) {
                int diff = nums[i] - nums[i + 1] + 1;
                nums[i + 1] = nums[i + 1] + diff;
                count += diff;
            }
        }
        return count;
    }

}
