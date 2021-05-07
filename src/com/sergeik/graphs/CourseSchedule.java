package com.sergeik.graphs;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you
 * must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */
public class CourseSchedule {

    public static void main(String[] args) {
        assert solution(2, new int[][]{
                {1,0}
        });
        assert !solution(2, new int[][]{
                {1,0},
                {0,1}
        });

        assert bfsTopologicalSort(2, new int[][]{
                {1,0}
        });
        assert !bfsTopologicalSort(2, new int[][]{
                {1,0},
                {0,1}
        });
    }

    private static boolean bfsTopologicalSort(int numCourses, int[][] prerequisites) {
        int[][] adjMatrix = new int[numCourses][numCourses];
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int ai = prerequisites[i][0]; //course B mast be taken before course A
            int bi = prerequisites[i][1];
            if (adjMatrix[bi][ai] == 0) {
                inDegree[ai]++;             //handles duplicates
            }
            adjMatrix[bi][ai] = 1;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (adjMatrix[course][i] != 0) {
                    if (--inDegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }

    private static boolean solution(int numCourses, int[][] prerequisites) {
        List<Integer> orderedCourses = new LinkedList<>();
        Set<Integer> noDepCourses = new HashSet<>();

        int[][] adjMatrix = new int[numCourses][numCourses];
        for (int[] preReq: prerequisites) {
            adjMatrix[preReq[0]][preReq[1]] = 1;
        }

        //build set of courses without dependencies
        findNoDepCourses(noDepCourses, adjMatrix);

        while (!noDepCourses.isEmpty()) {
            int nextCourse = noDepCourses.iterator().next();
            noDepCourses.remove(nextCourse);
            orderedCourses.add(nextCourse);
            for (int i = 0; i < numCourses; i++) {
                if (adjMatrix[nextCourse][i] == 1) {
                    adjMatrix[nextCourse][i] = 0;
                    if (!hasIncoming(i, adjMatrix))
                        noDepCourses.add(i);
                }
            }

        }

        return orderedCourses.size() == numCourses;
    }

    private static boolean hasIncoming(int course, int[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[i][course] == 1)
                return true;
        }
        return false;
    }

    private static void findNoDepCourses(Set<Integer> noDepCourses, int[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            boolean hasIncoming = false;
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[j][i] == 1) {
                    hasIncoming = true;
                    break;
                }
            }
            if (!hasIncoming)
                noDepCourses.add(i);
        }
    }

}
