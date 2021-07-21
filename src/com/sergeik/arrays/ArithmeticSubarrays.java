package com.sergeik.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A sequence of numbers is called arithmetic if it consists of at least two elements, and the difference
 * between every two consecutive elements is the same. More formally, a sequence s is arithmetic if and only
 * if s[i+1] - s[i] == s[1] - s[0] for all valid i.
 *
 * For example, these are arithmetic sequences:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic:
 *
 * 1, 1, 2, 5, 7
 * You are given an array of n integers, nums, and two arrays of m integers each, l and r, representing the m range
 * queries, where the ith query is the range [l[i], r[i]]. All the arrays are 0-indexed.
 *
 * Return a list of boolean elements answer, where answer[i] is true if the subarray nums[l[i]], nums[l[i]+1], ... ,
 * nums[r[i]] can be rearranged to form an arithmetic sequence, and false otherwise.
 */
public class ArithmeticSubarrays {

    public static void main(String[] args) {
        assert Arrays.equals(
                new Boolean[] {false,true,false,false,true,true},
                solution(new int[] {-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10}, new int[] {0,1,6,4,8,7}, new int[] {4,4,9,7,9,1}).toArray()
        );
        assert Arrays.equals(
                new Boolean[] {true, false, true},
                solution(new int[] {4,6,5,9,3,7}, new int[] {0,0,2}, new int[] {2,3,5}).toArray()
            );
    }

    private static List<Boolean> solution(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new LinkedList<>();
        for (int i = 0; i < l.length; i++)
            res.add(isArithmetic(nums, l[i], r[i]));
        return res;
    }

    private static Boolean isArithmetic(int[] nums, int l, int r) {
        if (r - l <= 1)
            return true;
        int[] subArray = Arrays.copyOfRange(nums, l, r + 1);
        Arrays.sort(subArray);

        int diff = subArray[1] - subArray[0];
        for (int i = 2; i < subArray.length; i++) {
            if (diff != subArray[i] - subArray[i - 1])
                return false;
        }
        return true;
    }

}
