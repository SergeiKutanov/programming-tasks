package com.sergeik.heap;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 *
 * Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0]
 * miles east of the starting position, and has station[i][1] liters of gas.
 *
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.
 * It uses 1 liter of gas per 1 mile that it drives.
 *
 * When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 *
 * What is the least number of refueling stops the car must make in order to reach its destination?
 * If it cannot reach the destination, return -1.
 *
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.
 * If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 */
public class MinimumNumberOfRefuelingStops {

    public static void main(String[] args) {
        assert 4 == solution(1000, 10, new int[][] {
                {7,217},{10,211},{17,146},{21,232},{25,310},{48,175},{53,23},{63,158},{71,292},{96,85},{100,302},
                {102,295},{116,110},{122,46},{131,20},{132,19},{141,13},{163,85},{169,263},{179,233},{191,169},
        {215,163},{224,231},{228,282},{256,115},{259,3},{266,245},{283,331},{299,21},{310,224},{315,188},{328,147},
        {345,74},{350,49},{379,79},{387,276},{391,92},{405,174},{428,307},{446,205},{448,226},{452,275},{475,325},
        {492,310},{496,94},{499,313},{500,315},{511,137},{515,180},{519,6},{533,206},{536,262},{553,326},{561,103},
        {564,115},{582,161},{593,236},{599,216},{611,141},{625,137},{626,231},{628,66},{646,197},{665,103},{668,8},
        {691,329},{699,246},{703,94},{724,277},{729,75},{735,23},{740,228},{761,73},{770,120},{773,82},{774,297},
        {780,184},{791,239},{801,85},{805,156},{837,157},{844,259},{849,2},{860,115},{874,311},{877,172},{881,109},
        {888,321},{894,302},{899,321},{908,76},{916,241},{924,301},{933,56},{960,29},{979,319},{983,325},{988,190},
        {995,299},{996,97}});
        assert 4 == solution(1000, 299, new int[][]{
                {13,21},{26,115},{100,47},{225,99},{299,141},
                {444,198},{608,190},{636,157},{647,255},{841,123}});
        assert 3 == solution(100, 25, new int[][] {{25,25}, {50,25}, {75,25}});
        assert -1 == solution(100, 1, new int[][] {});
        assert 0 == solution(1,1, new int[][] {});
        assert -1 == solution(100, 1, new int[][] {{10,100}});
        assert 2 == solution(100, 10, new int[][] {{10,60}, {20,30}, {30,30}, {60,40}});
    }

    /**
     * i is the index of next stops to refuel.
     * res is the times that we have refeuled.
     * pq is a priority queue that we store all available gas.
     *
     * We initial res = 0 and in every loop:
     *
     * We add all reachable stop to priority queue.
     * We pop out the largest gas from pq and refeul once.
     * If we can't refuel, means that we can not go forward and return -1
     *
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    private static int solution(int target, int startFuel, int[][] stations) {
        Queue<Integer> heap = new PriorityQueue<>();
        int i = 0, res;
        for (res = 0; startFuel < target; res++) {
            while (i < stations.length && stations[i][0] <= startFuel) {
                heap.offer(-stations[i++][1]);
            }
            if (heap.isEmpty())
                return -1;
            startFuel += -heap.poll();
        }
        return res;
    }

    /**
     * dp[t] means the furthest distance that we can get with t times of refueling.
     *
     * So for every station s[i],
     * if the current distance dp[t] >= s[i][0], we can refuel:
     * dp[t + 1] = max(dp[t + 1], dp[t] + s[i][1])
     *
     * In the end, we'll return the first t with dp[t] >= target,
     * otherwise we'll return -1.
     *
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    private static int dpSolution(int target, int startFuel, int[][] stations) {
        long[] dp = new long[stations.length + 1];
        dp[0] = startFuel;
        for (int i = 0; i < stations.length; i++) {
            for (int t = i; t >= 0 && dp[t] >= stations[i][0]; t--) {
                dp[t + 1] = Math.max(dp[t + 1], dp[t] + stations[i][1]);
            }
        }
        for (int t = 0; t < dp.length; t++)
            if (dp[t] >= target)
                return t;
        return -1;
    }

}
