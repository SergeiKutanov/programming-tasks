package com.sergeik.dynamic;


/**
 * There is a 3 lane road of length n that consists of n + 1 points labeled from 0 to n. A frog starts
 * at point 0 in the second lane and wants to jump to point n. However, there could be obstacles along the way.
 *
 * You are given an array obstacles of length n + 1 where each obstacles[i] (ranging from 0 to 3) describes
 * an obstacle on the lane obstacles[i] at point i. If obstacles[i] == 0, there are no obstacles at point i.
 * There will be at most one obstacle in the 3 lanes at each point.
 *
 * For example, if obstacles[2] == 1, then there is an obstacle on lane 1 at point 2.
 * The frog can only travel from point i to point i + 1 on the same lane if there is not an obstacle on the lane
 * at point i + 1. To avoid obstacles, the frog can also perform a side jump to jump to another lane
 * (even if they are not adjacent) at the same point if there is no obstacle on the new lane.
 *
 * For example, the frog can jump from lane 3 at point 3 to lane 1 at point 3.
 * Return the minimum number of side jumps the frog needs to reach any lane at point n starting from lane 2 at point 0.
 *
 * Note: There will be no obstacles on points 0 and n.
 */
public class MinimumSidewayJumps {

    public static void main(String[] args) {
        /*

        1   X   2   2   2
        0   0   X   3   3
        1   1   1   X   4

         */
        assert 2 == solution(new int[]{0,1,2,3,0});
    }

    private static int solution(int[] obstacles) {
        int[] dp = new int[]{1,0,1};
        for (int n: obstacles) {
            if (n > 0) {
                dp[n - 1] = 10000000;
            }
            for (int i = 0; i < 3; i++) {
                if (n != i + 1)
                    dp[i] = Math.min(dp[i], Math.min(dp[(i + 1) % 3], dp[(i + 2) % 3]) + 1);
            }
        }
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }

}
