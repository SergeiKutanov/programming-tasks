package com.sergeik.math;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given two integers memory1 and memory2 representing the available memory in bits on two memory sticks.
 * There is currently a faulty program running that consumes an increasing amount of memory every second.
 *
 * At the ith second (starting from 1), i bits of memory are allocated to the stick with more available
 * memory (or from the first memory stick if both have the same available memory). If neither stick has at
 * least i bits of available memory, the program crashes.
 *
 * Return an array containing [crashTime, memory1crash, memory2crash], where crashTime is the time (in seconds)
 * when the program crashed and memory1crash and memory2crash are the available bits of memory in the first
 * and second sticks respectively.
 */
public class IncrementalMemoryLeak {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{5, 3, 2},
                otherSolution(9, 6)
        );
        assert Arrays.equals(
                new int[]{6,0,4},
                otherSolution(8, 11)
        );
    }

    private static int[] otherSolution(int memory1, int memory2) {
        int time = 1;
        boolean overflow = false;
        while (true) {
            if (memory1 >= memory2) {
                memory1 -= time;
                if (memory1 < 0) {
                    overflow = true;
                    memory1 += time;
                }
            } else {
                memory2 -= time;
                if (memory2 < 0) {
                    overflow = true;
                    memory2 += time;
                }
            }
            if (overflow)
                return new int[]{time, memory1, memory2};
            time++;
        }
    }

    private static int[] solution(int memory1, int memory2) {

        /*

        diff = 3


        8 11    - 0
        8 10    - 1
        8 8     - 2
        5 8     - 3
        5 4     - 4
        0 4     - 5
        0 -1    - 6

        5 * 6 / 2 = 15
        6 * 7 / 2 = 21

        9   6   0
        8   6   1
        6   6   2
        3   6   3
        3   2   4
        -1  2   5

         */

//        int max = memory1 + memory2;
        // n * (n + 1) / 2
        // max * 2 = n * (n + 1) = n^2 + n

        Queue<int[]> pQueue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        pQueue.offer(new int[]{memory1, 0});
        pQueue.offer(new int[]{memory2, 1});
        int time = 0;
        while (true) {
            time++;
            int[] mem = pQueue.poll();
            if (mem[0] - time < 0) {
                int[] memOther = pQueue.poll();
                return new int[]{
                        time,
                        mem[1] == 0 ? mem[0] : memOther[0],
                        memOther[1] == 1 ? memOther[0] : mem[0]
                };
            }
            mem[0] -= time;
            pQueue.offer(mem);
        }
    }

}
