package com.sergeik.arrays;

import java.util.*;

/**
 * You are given an m x n integer matrix grid​​​.
 *
 * A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​.
 * The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell.
 * Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included
 * in each rhombus sum
 *
 * Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.
 *
 * Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three
 * distinct values, return all of them.
 *
 */
public class GetTheBiggestThreeRombusSumsInGrid {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[] {4, 1},
                solution(new int[][]{
                        {1,1,1,1},
                        {1,1,1,1},
                        {1,1,1,1},
                        {1,1,1,1}
                })
        );

        assert Arrays.equals(
                new int[] {7},
                solution(new int[][] {
                        {7,7,7}
                })
        );

        assert Arrays.equals(
                new int[] {20,9,8},
                solution(new int[][] {
                        {1,2,3},{4,5,6},{7,8,9}
                })
        );

        assert Arrays.equals(
                new int[] {228, 216, 211},
                solution(new int[][]{
                        {3,4,5,1,3},
                        {3,3,4,2,3},
                        {20,30,200,40,10},
                        {1,5,5,4,1},
                        {4,3,2,2,5}
                })
        );
    }

    private static int[] solution(int[][] grid) {
        Queue<Integer> sums = new PriorityQueue<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                tryRombus(grid, r, c, sums, 1);
            }
        }

        int[] res = new int[sums.size()];
        int idx = res.length - 1;
        while (!sums.isEmpty()) {
            res[idx--] = sums.poll();
        }
        return res;
    }

    private static void tryRombus(int[][] grid, int r, int c, Queue<Integer> sums, int size) {
        int sum = 0;
        if (size == 1) {
            sum = grid[r][c];
        } else {
            int row = r;
            int lc = c;
            int rc = c;
            for (int j = 1; j <= size; j++) {
                if (row < grid.length && lc >= 0 && rc < grid[row].length) {
                    sum += grid[row][lc];
                    if (lc != rc)
                        sum += grid[row][rc];
                } else {
                    return;
                }
                row++;
                lc--;
                rc++;
            }

            lc += 2;
            rc -= 2;

            for (int j = size - 1; j >= 1; j--) {
                if (row < grid.length && lc >= 0 && rc < grid[row].length) {
                    sum += grid[row][lc];
                    if (lc != rc)
                        sum += grid[row][rc];
                } else {
                    return;
                }
                row++;
                lc++;
                rc--;
            }
        }
        if (sums.size() > 2) {
            if (sums.peek() < sum) {
                if (!sums.contains(sum)) {
                    sums.offer(sum);
                    sums.poll();
                }
            }
        } else if (!sums.contains(sum)){
            sums.offer(sum);
        }
        tryRombus(grid, r, c, sums, size + 1);
    }

}
