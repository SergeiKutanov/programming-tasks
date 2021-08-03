package com.sergeik.arrays;

import java.util.*;

public class SortArrayByIncreasingFrequency {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {3,1,1,2,2,2}, solution(new int[] {1,1,2,2,2,3}));
    }

    private static int[] solution(int[] nums) {
        int[] count = new int[101];
        for (int n: nums)
            count[n]++;
        nums = Arrays.stream(nums)
                .boxed()
                .sorted((a, b) -> {
                    if (count[a] == count[b])
                        return b - a;
                    return count[a] - count[b];
                })
                .mapToInt(i -> i)
                .toArray();

        return nums;
    }

}
