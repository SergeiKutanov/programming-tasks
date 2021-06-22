package com.sergeik.greedy;

import java.util.PriorityQueue;

/**
 * You have some number of sticks with positive integer lengths. These lengths are given as an array sticks,
 * where sticks[i] is the length of the ith stick.
 *
 * You can connect any two sticks of lengths x and y into one stick by paying a cost of x + y. You must connect
 * all the sticks until there is only one stick remaining.
 *
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 */
public class MinimumCostToConnectSticks {

    public static void main(String[] args) {
        assert 0 == solution(new int[] {5});
        assert 14 == solution(new int[] {2,4,3});
        assert 30 == solution(new int[] {1,8,3,5});
    }

    private static int solution(int[] sticks) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int n: sticks)
            heap.offer(n);

        int res = 0;
        while (heap.size() > 1) {
            int x = heap.poll();
            int y = heap.poll();
            int cRes =x + y;
            res += cRes;
            heap.offer(cRes);
        }

        return res;
    }

}
