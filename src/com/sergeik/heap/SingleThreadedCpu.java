package com.sergeik.heap;

import java.util.*;

/**
 * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks,
 * where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process
 * at enqueueTimei and will take processingTimei to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
 * If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 */
public class SingleThreadedCpu {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[]{15,14,13,1,6,3,5,12,8,11,9,4,10,7,0,2},
                solution(new int[][] {
                        {35,36},{11,7},{15,47},{34,2},{47,19},{16,14},{19,8},
                        {7,34},{38,15},{16,18},{27,22},{7,15},{43,2},{10,5},{5,4},{3,11}
                })
        );

        assert Arrays.equals(
                new int[] {6,1,2,9,4,10,0,11,5,13,3,8,12,7},
                solution(new int[][] {
                        {19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},
                        {38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}
                })
        );

        assert Arrays.equals(
                new int[]{0,2,3,1},
                solution(new int[][] {
                        {1,2},
                        {2,4},
                        {3,2},
                        {4,1}
                })
        );

        assert Arrays.equals(
                new int[]{4,3,2,0,1},
                solution(new int[][] {
                        {7,10},
                        {7,12},
                        {7,5},
                        {7,4},
                        {7,2}
                })
        );
    }

    private static int[] solution(int[][] tasks) {

        Queue<int[]> backlog = new PriorityQueue<>((a,b) -> {
            if (a[1] == b[1])
                return a[2] - b[2];
            return a[1] - b[1];
        });

        int[][] sortedTasks = new int[tasks.length][3];
        for (int i = 0; i < tasks.length; i++) {
            int[] task = tasks[i];
            sortedTasks[i] = new int[] {task[0], task[1], i};
        }
        Arrays.sort(sortedTasks, (a, b) -> {
            return a[0] - b[0];
        });

        int time = 0;
        int futureTaskRunner = 0;
        int[] res = new int[tasks.length];

        int processedTasks = 0;
        while(processedTasks < tasks.length) {
            while (futureTaskRunner < tasks.length && sortedTasks[futureTaskRunner][0] <= time) {
                backlog.offer(sortedTasks[futureTaskRunner++]);
            }
            if (backlog.isEmpty()) {
                time = sortedTasks[futureTaskRunner][0];
                continue;
            }
            int[] nextTask = backlog.poll();
            res[processedTasks++] = nextTask[2];
            time += nextTask[1];
        }
        return res;
    }

}
