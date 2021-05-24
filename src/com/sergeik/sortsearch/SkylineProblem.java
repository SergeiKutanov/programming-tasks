package com.sergeik.sortsearch;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed
 * from a distance. Given the locations and heights of all the buildings, return the skyline formed by
 * these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where
 * buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form
 * [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except
 * the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination
 * where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part
 * of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance,
 * [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged
 * into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 */
public class SkylineProblem {

    public static void main(String[] args) {
        List<List<Integer>> res = solution(new int[][]{
                {2,9,10},
                {3,7,15},
                {5,12,12},
                {15,20,10},
                {19,24,8}
        });
        List<List<Integer>> expected = new LinkedList<>();
        expected.add(Arrays.asList(2,10));
        expected.add(Arrays.asList(3,15));
        expected.add(Arrays.asList(7,12));
        expected.add(Arrays.asList(12,0));
        expected.add(Arrays.asList(15,10));
        expected.add(Arrays.asList(20,8));
        expected.add(Arrays.asList(24,0));
        for (int i = 0; i < expected.size(); i++) {
            assert Arrays.equals(expected.get(i).toArray(), res.get(i).toArray());
        }
    }

    private static List<List<Integer>> solution(int[][] buildings) {
        List<List<Integer>> skyline = new LinkedList<>();
        List<int[]> heights = new LinkedList<>();
        for (int[] building : buildings) {
            heights.add(new int[]{building[0], -building[2]});
            heights.add(new int[]{building[1], building[2]});
        }

        Collections.sort(heights, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        Queue<Integer> pQueue = new PriorityQueue<>((a, b) -> b - a);
        pQueue.offer(0);
        int prev = 0;
        for (int[] height : heights) {
            if (height[1] < 0) {
                pQueue.offer(-height[1]);
            } else {
                pQueue.remove(height[1]);
            }
            int currMax = pQueue.peek();
            if (currMax != prev) {
                skyline.add(Arrays.asList(height[0], currMax));
                prev = currMax;
            }
        }

        return skyline;
    }

}
