package com.sergeik.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given two 0-indexed integer arrays servers and tasks of lengths n​​​​​​ and m​​​​​​ respectively. servers[i]
 * is the weight of the i​​​​​​th​​​​ server, and tasks[j] is the time needed to process the j​​​​​​th​​​​ task in seconds.
 *
 * You are running a simulation system that will shut down after all tasks are processed. Each server can only
 * process one task at a time. You will be able to process the jth task starting from the jth second beginning
 * with the 0th task at second 0. To process task j, you assign it to the server with the smallest weight that
 * is free, and in case of a tie, choose the server with the smallest index. If a free server gets assigned
 * task j at second t,​​​​​​ it will be free again at the second t + tasks[j].
 *
 * If there are no free servers, you must wait until one is free and execute the free tasks as soon as possible.
 * If multiple tasks need to be assigned, assign them in order of increasing index.
 *
 * You may assign multiple tasks at the same second if there are multiple free servers.
 *
 * Build an array ans​​​​ of length m, where ans[j] is the index of the server the j​​​​​​th task will be assigned to.
 *
 * Return the array ans​​​​.
 */
public class ProcessTasksUsingServers {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[] {12,7,10,14,11,15,1,2,8,17,4},
                solution(
                        new int[] {74,57,61,82,67,97,67,21,61,79,21,50,14,88,48,52,76,64},
                        new int[] {21,100,48,64,20,8,28,10,3,63,7}
                )
        );

        assert Arrays.equals(
                new int[]{2,2,0,2,1,2},
                solution(
                        new int[] {3,3,2},
                        new int[] {1,2,3,2,1,2}
                )
        );

        assert Arrays.equals(
                new int[]{1,4,1,4,1,3,2},
                solution(
                        new int[] {5,1,4,3,2},
                        new int[] {2,1,2,4,5,2,1}
                    )
        );
    }

    private static int[] solution(int[] servers, int[] tasks) {
        Queue<int[]> availableServers = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });
        Queue<int[]> busyServers = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < servers.length; i++)
            availableServers.offer(new int[]{servers[i], i});

        int[] res = new int[tasks.length];
        int time = 0;
        int taskIdx = 0;
        while (taskIdx < tasks.length) {
            while (!busyServers.isEmpty() && busyServers.peek()[2] <= time) {
                int[] server = busyServers.poll();
                availableServers.offer(new int[] {server[0], server[1]});
            }
            while (taskIdx < tasks.length && taskIdx <= time && !availableServers.isEmpty()) {
                int[] server = availableServers.poll();
                res[taskIdx] = server[1];
                int finishTime = time + tasks[taskIdx];
                busyServers.offer(new int[] {server[0], server[1], finishTime});
                taskIdx++;
            }
            if (!availableServers.isEmpty())
                time = taskIdx;
            else
                time = busyServers.peek()[2];
        }
        return res;
    }

}
