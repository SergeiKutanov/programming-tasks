package com.sergeik.arrays;

import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */
public class KthLargestElement {

    public static void main(String[] args) {
        assert 5 == solution(new int[]{3,2,1,5,6,4}, 2);
        assert 4 == solution(new int[]{3,2,3,1,2,4,5,5,6}, 4);
    }

    private static int solution(int[] nums, int k) {
        k = nums.length - k;
        int lowBound = 0;
        int upperBound = nums.length - 1;
        while (lowBound < upperBound) {
            int middle = partition(nums, lowBound, upperBound);
            if (middle < k) {
                lowBound = middle + 1;
            } else if (middle > k) {
                upperBound = middle - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private static int partition(int[] nums, int lo, int hi) {
        int lowerBound = lo;
        int upperBound = hi + 1;
        while (true) {
            while (lowerBound < hi && nums[++lowerBound] < nums[lo]); //find next element bigger than lo
            while (upperBound > lo && nums[lo] < nums[--upperBound]); // find next element smaller than lo
            if (lowerBound >= upperBound)
                break;
            int tmp = nums[lowerBound];
            nums[lowerBound] = nums[upperBound];
            nums[upperBound] = tmp;
        }
        int tmp = nums[lo];
        nums[lo] = nums[upperBound];
        nums[upperBound] = tmp;
        return upperBound;
    }

    private static int pQueueSolution(int[] nums, int k) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int n: nums) {
            pQueue.offer(n);
        }
        while (k > 1) {
            k--;
            pQueue.poll();
        }
        return pQueue.poll();
    }

}
