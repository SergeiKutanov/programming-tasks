package com.sergeik.arrays;

/**
 * There is a restaurant with a single chef. You are given an array customers, where customers[i] = [arrivali, timei]:
 *
 * arrivali is the arrival time of the ith customer. The arrival times are sorted in non-decreasing order.
 * timei is the time needed to prepare the order of the ith customer.
 * When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle.
 * The customer waits till the chef finishes preparing his order. The chef does not prepare food for more than
 * one customer at a time. The chef prepares food for customers in the order they were given in the input.
 *
 * Return the average waiting time of all customers. Solutions within 10-5 from the actual answer
 * are considered accepted.
 */
public class AverageWaitingTime {

    public static void main(String[] args) {

        assert Math.abs(4.16667 - solution(new int[][] {
                {2,3},
                {6,3},
                {7,5},
                {11,3},
                {15,2},
                {18,1}
        })) < 0.00001;

        assert 5.0 == solution(new int[][]{
                {1,2},
                {2,5},
                {4,3}
        });

        assert 3.25 == solution(new int[][]{
                {5,2},
                {5,4},
                {10,3},
                {20,1}
        });
    }

    private static double solution(int[][] customers) {
        int nextTime = 0;
        long waitingTime = 0;
        for (int i = 0; i < customers.length; i++) {
            int waitTime = Math.max(0, nextTime  - customers[i][0]) + customers[i][1];
            waitingTime += waitTime;
            nextTime = Math.max(nextTime, customers[i][0]) + customers[i][1];
        }
        return waitingTime / (double) customers.length;
    }

}
