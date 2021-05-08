package com.sergeik.graphs;

import java.util.*;

public class CourseScheduleTwo {

    public static void main(String[] args) {
        assert verify(new int[]{0,1,2,3}, solutionAdjList(4, new int[][]{
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        }));

        assert verify(new int[]{1,0}, solutionAdjList(2, new int[][]{}));
    }

    private static int[] solutionAdjList(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] inDegree = new int[numCourses];

        /*
            build adj list of the graph where:
            key - course
            value - list of courses which depend on key course

            inDegree stores the count of dependencies for a course at index
         */
        for (int[] preReq: prerequisites) {
            List<Integer> deps = adjMap.getOrDefault(preReq[1], new LinkedList<>());
            deps.add(preReq[0]);
            adjMap.put(preReq[1], deps);
            inDegree[preReq[0]]++;
        }

        /*
        find courses without dependencies and add them to the processing queue
         */
        int[] res = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        int resCounter = 0;
        while (!queue.isEmpty()) {
            int nextCourse = queue.poll();
            res[resCounter++] = nextCourse;
            if (adjMap.containsKey(nextCourse)) {
                /*
                for each neighbour of the course decrease inDegree
                 */
                for (int n: adjMap.get(nextCourse)) {
                    if (--inDegree[n] == 0)
                        queue.offer(n);
                }
            }
        }

        return resCounter == numCourses ? res : new int[0];

    }

    private static boolean verify(int[] n1, int[] n2) {
        if (n1.length != n2.length)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int n: n1)
            set.add(n);

        for (int n: n2)
            if (!set.contains(n))
                return false;
        return true;
    }

}
