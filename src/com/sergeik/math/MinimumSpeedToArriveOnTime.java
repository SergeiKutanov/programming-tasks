package com.sergeik.math;

/**
 * You are given a floating-point number hour, representing the amount of time you have to reach the office.
 * To commute to the office, you must take n trains in sequential order. You are also given an integer array dist
 * of length n, where dist[i] describes the distance (in kilometers) of the ith train ride.
 *
 * Each train can only depart at an integer hour, so you may need to wait in between each train ride.
 *
 * For example, if the 1st train ride takes 1.5 hours, you must wait for an additional 0.5 hours before you can
 * depart on the 2nd train ride at the 2 hour mark.
 * Return the minimum positive integer speed (in kilometers per hour) that all the trains must travel at for you
 * to reach the office on time, or -1 if it is impossible to be on time.
 *
 * Tests are generated such that the answer will not exceed 107 and hour will have at most two digits
 * after the decimal point.
 */
public class MinimumSpeedToArriveOnTime {

    public static void main(String[] args) {
        assert 10000000 == solution(new int[]{1,1,100000}, 2.01);
        assert -1 == solution(new int[] {1,3,2}, 1.9);
        assert 3 == solution(new int[] {1,3,2}, 2.7);
        assert 1 == solution(new int[] {1,3,2}, 6);
    }

    private static int solution(int[] dist, double hour) {
        int min = 1;
        int ans = -1;
        int max = 10_000_000;

        if (dist.length - 1 > hour)
            return -1;

        while (min <= max) {
            int middle = (max + min) / 2;
            double time = 0;
            for (int i = 0; i < dist.length - 1; i++) {
                time += Math.ceil(((double) dist[i]) / middle);
            }
            time += (((double) dist[dist.length - 1]) / middle);
            if (time > hour) {
                min = middle + 1;
            } else {
                ans = middle;
                max = middle - 1;
            }
        }

        return ans;
    }

}
