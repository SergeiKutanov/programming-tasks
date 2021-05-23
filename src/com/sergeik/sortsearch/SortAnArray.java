package com.sergeik.sortsearch;

import java.util.Arrays;

public class SortAnArray {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{1,2,3,5},
                solution(new int[]{5,2,3,1})
        );
        assert Arrays.equals(
                new int[]{0,0,1,1,2,5},
                solution(new int[]{5,1,1,2,0,0})
        );
    }

    private static int[] solution(int[] nums) {
        return mergeSort(0, nums.length, nums);
    }

    private static int[] mergeSort(int start, int end, int[] nums) {
        if (end - start == 1)
            return new int[]{nums[start]};
        int middle = (start + end) / 2;
        int[] left = mergeSort(start, middle, nums);
        int[] right = mergeSort(middle, end, nums);
        int[] sorted = new int[left.length + right.length];
        int sIndex = 0;
        int lIndex = 0;
        int rIndex = 0;
        while (lIndex < left.length && rIndex < right.length) {
            if (left[lIndex] < right[rIndex]) {
                sorted[sIndex++] = left[lIndex++];
            } else {
                sorted[sIndex++] = right[rIndex++];
            }
        }
        while (lIndex < left.length) {
            sorted[sIndex++] = left[lIndex++];
        }
        while (rIndex < right.length) {
            sorted[sIndex++] = right[rIndex++];
        }
        return sorted;
    }

}
