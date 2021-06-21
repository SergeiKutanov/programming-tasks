package com.sergeik.math;

import java.util.PriorityQueue;

/**
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.
 *
 * The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color
 * you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for the
 * first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is then
 * valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).
 *
 * You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color
 * that you initially own. You are also given an integer orders, which represents the total number of balls that
 * the customer wants. You can sell the balls in any order.
 *
 * Return the maximum total value that you can attain after selling orders colored balls. As the answer may be
 * too large, return it modulo 109 + 7.
 */
public class SellDiminishingValuedColoredBalls {

    public static void main(String[] args) {
        assert 373219333 == solution(new int[] {497978859,167261111,483575207,591815159},836556809);
        assert 21 == solution(new int[]{1000000000}, 1000000000);
        assert 19 == solution(new int[]{3,5}, 6);
        assert 14 == solution(new int[]{2,5}, 4);
        assert 110 == solution(new int[]{2,8,4,10,6}, 20);
    }

    private static int solution(int[] inventory, int orders) {
        int mod = 1000000007;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int num : inventory) {
            pq.offer(num);
        }

        int cur = pq.poll();
        int count = 1; // When pq poll the next element, +1 to count.
        long res = 0;
        while (orders > 0) {
            int next = pq.isEmpty() ? 0 : pq.peek();
            // If the number for [next + 1, cur] less than orders, add them ALL.
            int itemsToBuy = (cur - next) * count;
            if (itemsToBuy <= orders) {
                // Add all the sum, and don't forget cast!
                long num =  (1L * (next + 1 + cur) * itemsToBuy / 2) % mod;
                res = (res + num) % mod;
                orders -= itemsToBuy;

            } else {
                // If the number for [next + 1, cur] greater than orders, only add partially.
                // Calculate the new next where the add stops.
                next = cur - orders / count;
                long num =  (1L * (next + 1 + cur) * (cur - next) * count / 2) % mod;
                res = (res + num) % mod;
                // For the last number to add (new next), and don't forget cast! I forget here in contest!
                res = (res + 1L * next * (orders % count)) % mod;
                orders = 0;
            }

            if (!pq.isEmpty()) cur = pq.poll();
            count++;
        }
        return (int) res;
    }

}
