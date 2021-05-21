package com.sergeik.graphs;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are n different online courses numbered from 1 to n. You are given an array courses where 
 * courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi 
 * days and must be finished before or on lastDayi.
 *
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 *
 * Return the maximum number of courses that you can take.
 */
public class CourseScheduleIII {

    public static void main(String[] args) {

        //[9,10][3,12][7,17][4,18][10,19][5,20][10,20]
        assert 4 == solution(new int[][]{
                {7,17},{3,12},{10,20},{9,10},{5,20},{10,19},{4,18}
        });

        assert 2 == solution(new int[][]{
                {5,5},{4,6},{2,6}
        });
        assert 0 == solution(new int[][]{
                {3,2},
                {4,3}
        });
        assert 1 == solution(new int[][] {{1,2}});
        assert 3 == solution(new int[][] {
                {100,200},{200,1300},{1000,1250},{2000,3200}
        });
    }

    private static int solution(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int currentTime = 0;
        int maxCount = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < courses.length; i++) {
            if (currentTime + courses[i][0] <= courses[i][1]) {
                currentTime += courses[i][0];
                maxCount++;
                queue.offer(courses[i][0]);
            } else {
                if (!queue.isEmpty() && queue.peek() > courses[i][0]) {
                    currentTime -= queue.poll();
                    currentTime += courses[i][0];
                    queue.add(courses[i][0]);
                }
            }
        }
        return maxCount;
    }
    
}
