package com.sergeik.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n.
 * There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency
 * of the ith engineer respectively.
 *
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency
 * among their engineers.
 *
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 *
 *
 */
public class MaximumPerformanceOfATeam {

    public static void main(String[] args) {

        assert 90 == solution(
                7,
                new int[] {4,7,1,5,6,7,2},
                new int[] {2,9,10,3,3,5,9},
                3
        );

        assert 72 == solution(6,
                new int[] {2,10,3,1,5,8},
                new int[] {5,4, 3,9,7,2},
                4
            );

        assert 60 == solution(6,
                new int[] {2,10,3,1,5,8},
                new int[] {5,4,3,9,7,2},
                2
        );

        //(10 + 5 + 2) * min(4,7,5)
        assert 68 == solution(6,
                new int[] {2,10,3,1,5,8},
                new int[] {5,4,3,9,7,2},
                3
            );
    }

    private static int solution(int n, int[] speed, int[] efficiency, int k) {
        int[][] eff = new int[n][2];
        for (int i = 0; i < n; i++) {
            eff[i] = new int[]{efficiency[i], speed[i]};
        }
        Arrays.sort(eff, (a,b) -> b[0] - a[0]);

        PriorityQueue<Integer> team = new PriorityQueue<>(k, (a,b) -> {
            return a - b;
        });
        long res = 0;
        long speedSum = 0;
        for (int[] ef: eff) {
            team.add(ef[1]);
            speedSum += ef[1];
            if (team.size() > k) {
                speedSum -= team.poll();
            }
            res = Math.max(res, speedSum * ef[0]);
        }
        return (int)(res % 1000000007);
    }

}
