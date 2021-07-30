package com.sergeik.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given an array of people, people, which are the attributes of some people in a queue (not necessarily
 * in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front
 * who have a height greater than or equal to hi.
 *
 * Reconstruct and return the queue that is represented by the input array people. The returned queue should be
 * formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue
 * (queue[0] is the person at the front of the queue).
 */
public class QueueReconstructionByHeight {

    public static void main(String[] args) {
        int[][] exp, res;
        exp = new int[][] {
                {5,0}, {7,0}, {5,2}, {6,1}, {4,4}, {7,1}
        };
        res = solution(new int[][] {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}});
        for (int i = 0; i < exp.length; i++)
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res[i][j];
    }

    private static int[][] solution(int[][] people) {
        Arrays.sort(people, (a,b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return b[0] - a[0];
        });
        List<int[]> res = new ArrayList<>();
        for (int[] p: people) {
            res.add(p[1], p);
        }
        return res.toArray(new int[people.length][2]);
    }

}
