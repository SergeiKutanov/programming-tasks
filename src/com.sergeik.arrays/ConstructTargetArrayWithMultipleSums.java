package com.sergeik.arrays;

import java.util.PriorityQueue;

/**
 * Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :
 *
 * let x be the sum of all elements currently in your array.
 * choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
 * You may repeat this procedure as many times as needed.
 * Return True if it is possible to construct the target array from A otherwise return False.
 */
public class ConstructTargetArrayWithMultipleSums {

    public static void main(String[] args) {
        assert !isPossible(new int[]{2,900000002});
        assert isPossible(new int[]{8,5});
        assert isPossible(new int[]{9,3,5});
        assert !isPossible(new int[]{1,1,1,2});
    }

    private static boolean isPossible(int[] target) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>((a, b) -> b - a);
        long total = 0;
        for (int n: target) {
            total += n;
            pQueue.add(n);
        }

        while (true) {
            int number = pQueue.poll();
            total -= number;
            if (number == 1 || total == 1)
                return true;
            if (number < total || total == 0 || number % total == 0)
                return false;
            number %= total;
            total += number;
            pQueue.add(number);
        }
    }


}
