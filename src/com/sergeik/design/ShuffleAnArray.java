package com.sergeik.design;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 */
public class ShuffleAnArray {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        Solution solution = new Solution(nums.clone());
        int[] firstShuffle = solution.shuffle();
        int[] resetData = solution.reset();
        int[] secondShuffle = solution.shuffle();

        assert verifyArrayContents(nums, firstShuffle);
        assert Arrays.equals(nums, resetData);
        assert verifyArrayContents(nums, secondShuffle);

    }

    private static boolean verifyArrayContents(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            return false;
        for (int n: arr1) {
            boolean found = false;
            for (int j: arr2) {
                if (n == j) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }

}

class Solution {

    private Random random = new Random();
    private int[] nums;

    public Solution(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return nums;
    }

    public int[] fisherYatesShuffle() {
        int[] shuffled = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            int index = random.nextInt(nums.length - i) + i;
            int tmp = shuffled[index];
            shuffled[index] = shuffled[i];
            shuffled[i] = tmp;
        }
        return shuffled;
    }

    private int[] bruteForce() {
        int[] shuffled = new int[nums.length];
        Set<Integer> inserted = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int nextIndex = getNextIndex(inserted);
            inserted.add(i);
            shuffled[i] = nums[nextIndex];
        }
        System.out.println("Shuffled: " + Arrays.toString(shuffled));

        return shuffled;
    }

    public int[] shuffle() {
//        return bruteForce();
        return fisherYatesShuffle();
    }

    private int getNextIndex(Set<Integer> inserted) {
        boolean found = false;
        Integer nextIndex = null;
        while (!found) {
            nextIndex = random.nextInt(nums.length);
            if (!inserted.contains(nextIndex)) {
                inserted.add(nextIndex);
                found = true;
            }
        }
        return nextIndex;
    }

}
