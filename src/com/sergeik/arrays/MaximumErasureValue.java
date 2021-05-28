package com.sergeik.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
 * The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal
 * to a[l],a[l+1],...,a[r] for some (l,r).
 */
public class MaximumErasureValue {

    public static void main(String[] args) {
        assert 10000 == solution(new int[]{10000});
        assert 17 == solution(new int[]{4,2,4,5,6});
        assert 8 == solution(new int[]{5,2,1,2,5,2,1,2,5});
    }

    private static int solution(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        boolean[] seen = new boolean[10001];
        int i = 0, j = 0, max = nums[0], cSum = 0;
        while (j < nums.length - 1) {
            cSum += nums[j];
            seen[nums[j]] = true;
            while (j < nums.length - 1 && !seen[nums[j + 1]]) {
                j++;
                seen[nums[j]] = true;
                cSum += nums[j];
            }
            max = Math.max(max, cSum);
            j++;
            while (j < nums.length && i < j && nums[i] != nums[j]) {
                cSum -= nums[i];
                seen[nums[i]] = false;
                i++;
            }
            cSum -= nums[i];
            seen[nums[i]] = false;
            i++;
        }
        return max;
    }
}
