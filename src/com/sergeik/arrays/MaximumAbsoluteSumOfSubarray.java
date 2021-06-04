package com.sergeik.arrays;

public class MaximumAbsoluteSumOfSubarray {

    public static void main(String[] args) {
        assert 5 == solution(new int[] {1,-3,2,3,-4});
        assert 8 == solution(new int[] {2,-5,1,-4,3,-2});
    }

    private static int solution(int[] nums) {
        int maxSoFar = 0, maxEnd = 0, minSoFar = 0, minEnd = 0;
        for (int i = 0; i < nums.length; i++) {
            maxEnd = maxEnd + nums[i];
            if (maxEnd < 0)
                maxEnd = 0;
            if (maxSoFar < maxEnd)
                maxSoFar = maxEnd;

            minEnd = minEnd + nums[i];
            if (minEnd > 0)
                minEnd = 0;
            if (minSoFar > minEnd)
                minSoFar = minEnd;
        }
        return Math.max(Math.abs(minSoFar), Math.abs(maxSoFar));
    }

}
