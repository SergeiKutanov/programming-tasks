package com.sergeik.heap;

import java.util.PriorityQueue;

/**
 * There is a special kind of apple tree that grows apples every day for n days. On the ith day,
 * the tree grows apples[i] apples that will rot after days[i] days, that is on day i + days[i]
 * the apples will be rotten and cannot be eaten. On some days, the apple tree does not grow any apples,
 * which are denoted by apples[i] == 0 and days[i] == 0.
 *
 * You decided to eat at most one apple a day (to keep the doctors away). Note that you can keep eating
 * after the first n days.
 *
 * Given two integer arrays days and apples of length n, return the maximum number of apples you can eat.
 */
public class MaximumNumberOfEatenApples {

    public static void main(String[] args) {

        assert 5 == solution(
                new int[] {3,0,0,0,0,2},
                new int[] {3,0,0,0,0,2}
        );

        assert 7 == solution(
                new int[] {1,2,3,5,2},
                new int[] {3,2,1,4,2}
        );
    }

    private static int solution(int[] apples, int[] days) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        int day = 0;
        int count = 0;
        while (day < apples.length || !heap.isEmpty()) {
            if (day < apples.length && apples[day] > 0) {
                heap.offer(new int[] {day + days[day], apples[day]});
            }

            while (!heap.isEmpty() && heap.peek()[0] <= day)
                heap.poll();

            if (!heap.isEmpty()) {
                count++;
                int[] app = heap.poll();
                app[1]--;
                if (app[1] > 0)
                    heap.offer(app);
            }

            day++;
        }
        return count;
    }

}
