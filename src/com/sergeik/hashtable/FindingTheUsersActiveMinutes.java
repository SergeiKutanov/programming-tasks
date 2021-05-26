package com.sergeik.hashtable;

import java.util.*;

/**
 * You are given the logs for users' actions on LeetCode, and an integer k. The logs are represented
 * by a 2D integer array logs where each logs[i] = [IDi, timei] indicates that the user with IDi performed
 * an action at the minute timei.
 *
 * Multiple users can perform actions simultaneously, and a single user can perform multiple actions in the same minute.
 *
 * The user active minutes (UAM) for a given user is defined as the number of unique minutes in which the
 * user performed an action on LeetCode. A minute can only be counted once, even if multiple actions occur during it.
 *
 * You are to calculate a 1-indexed array answer of size k such that, for each j (1 <= j <= k), answer[j]
 * is the number of users whose UAM equals j.
 *
 * Return the array answer as described above.
 */
public class FindingTheUsersActiveMinutes {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{0,2,0,0,0},
                solution(new int[][]{
                        {0,5},
                        {1,2},
                        {0,2},
                        {0,5},
                        {1,3}
                }, 5)
        );
        assert Arrays.equals(
                new int[]{1,1,0,0},
                solution(new int[][] {
                        {1,1},
                        {2,2},
                        {2,3}
                }, 4)
        );
    }

    private static int[] solution(int[][] logs, int k) {
        int[] res = new int[k];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] rec: logs) {
            if (!map.containsKey(rec[0]))
                map.put(rec[0], new HashSet<>());
            map.get(rec[0]).add(rec[1]);
        }
        for (Integer user: map.keySet()) {
            res[map.get(user).size() - 1]++;
        }
        return res;
    }

}
