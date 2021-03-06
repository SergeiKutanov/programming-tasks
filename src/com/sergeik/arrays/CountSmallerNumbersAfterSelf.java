package com.sergeik.arrays;

import com.sergeik.linkedlists.ListHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 */
public class CountSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        List<Integer> res = bitSolution(new int[]{5,2,6,1});
        assert ListHelper.compareIntLists(Arrays.asList(2,1,1,0), res);
    }

    private static List<Integer> bitSolution(int[] nums) {
        int offset = 10000; //offset negative numbers
        int size = 2 * 10001;
        int[] tree = new int[size];
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int smallerCount = query(nums[i] + offset, tree);
            res.add(smallerCount);
            update(nums[i] + offset, 1, tree, size);
        }
        Collections.reverse(res);
        return res;
    }

    private static void update(int index, int val, int[] tree, int size) {
        index++;
        while (index < size) {
            tree[index] += val;
            index += index & -index;
        }
    }

    private static int query(int idx, int[] tree) {
        int res = 0;
        while (idx > 0) {
            res += tree[idx];
            idx -= idx & -idx;
        }
        return res;
    }


    private static List<Integer> mergeSortSolution(int[] nums) {

        if (nums.length == 0)
            return new ArrayList<>();

        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(i, nums[i]);
        }

        Integer[] res = new Integer[nums.length];
        Arrays.fill(res, 0);
        mergeSort(res, pairs);

        List<Integer> ret = new ArrayList<>();
        ret.addAll(Arrays.asList(res));

        return ret;
    }

    /**
     * The idea is to count number of steps a greater element makes to the right
     * This will yield the number of elements smaller than a particular greater element
     * Essentially - merge sort with number of steps counted on the fly
     * @param result
     * @param pairs
     * @return
     */
    private static Pair[] mergeSort(Integer[] result, Pair[] pairs) {
        if (pairs.length <= 1)
            return pairs;

        int middle = pairs.length / 2;
        Pair[] left = mergeSort(result, Arrays.copyOfRange(pairs, 0, middle));
        Pair[] right = mergeSort(result, Arrays.copyOfRange(pairs, middle, pairs.length));

        for (int i = 0, j = 0; i < left.length || j < right.length;) {
            if (j == right.length || i < left.length && left[i].v <= right[j].v) {
                pairs[i + j] = left[i];
                result[left[i++].i] += j;
            } else {
                pairs[i + j] = right[j++];
            }
        }

        return pairs;
    }

    static class Pair {
        int v;
        int i;

        Pair(int i, int v) {
            this.i = i;
            this.v = v;
        }

    }

}
