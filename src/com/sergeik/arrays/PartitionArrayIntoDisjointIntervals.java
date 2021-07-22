package com.sergeik.arrays;

/**
 * Given an array nums, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 */
public class PartitionArrayIntoDisjointIntervals {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {26,51,40,58,42,76,30,48,79,91});
        assert 1 == solution(new int[] {1,1});
        assert 3 == solution(new int[] {5,0,3,8,6});
        assert 4 == solution(new int[] {1,1,1,0,6,12});
    }

    private static int solution(int[] nums) {
        //  26  51  51  58  58  76  76  76  79  91
        //  26  30  30  30  30  30  30  48  79  91
        //  0   21  21  28  28  46  46  28  0   0

        //  5   5   5   8   8
        //  0   0   3   6   6
        //  5   5   2   2   2

        //  1   1   1   1   6   12
        //  0   0   0   0   6   12
        int[] maxSoFar = new int[nums.length];
        maxSoFar[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            maxSoFar[i] = Math.max(maxSoFar[i - 1], nums[i]);
        int[] minSoFar = new int[nums.length];
        minSoFar[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--)
            minSoFar[i] = Math.min(minSoFar[i + 1], nums[i]);

        for (int i = 1; i < nums.length; i++) {
            if (maxSoFar[i - 1] <= minSoFar[i])
                return i;
        }
        return -1;
    }

}
