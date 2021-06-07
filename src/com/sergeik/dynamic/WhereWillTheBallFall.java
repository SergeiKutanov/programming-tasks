package com.sergeik.dynamic;


import java.util.Arrays;
import java.util.Stack;

/**
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the
 * top and bottom sides.
 *
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball
 * to the right or to the left.
 *
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and
 * is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and
 * is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out
 * of the bottom. A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects
 * the ball into either wall of the box.
 *
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom
 * after dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
 */
public class WhereWillTheBallFall {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[] {1,-1,-1,-1,-1},
                solution(new int[][] {
                        {1,1,1,-1,-1},
                        {1,1,1,-1,-1},
                        {-1,-1,-1,1,1},
                        {1,1,1,1,-1},
                        {-1,-1,-1,-1,-1}
                })
        );

        assert Arrays.equals(
                new int[] {0,1,2,3,4,-1},
                solution(new int[][] {
                        {1,1,1,1,1,1},
                        {-1,-1,-1,-1,-1,-1},
                        {1,1,1,1,1,1},
                        {-1,-1,-1,-1,-1,-1}
                })
        );
    }

    private static int[] solution(int[][] grid) {
        int cols = grid[0].length;
        int[] res = new int[cols];

        for(int c = 0; c < cols; c++)
            res[c]=dfs(grid,0, c);

        return res;
    }

    private static int dfs(int[][] grid, int r, int c){
        if(r == grid.length)
            return c;

        if(c < 0 || c == grid[0].length)
            return -1;

        if(grid[r][c] == 1 && c + 1 < grid[0].length && grid[r][c + 1] == 1)
            return dfs(grid,r + 1,c + 1);

        else if(grid[r][c] == -1 && c - 1 >= 0 && grid[r][c - 1] == -1)
            return dfs(grid,r + 1,c - 1);

        return -1;
    }

    /**
     * 1 - top left to bottome right
     * -1 - to right to bottom left
     * @param grid
     * @return
     */
    private static int[] pathTracingSolution(int[][] grid) {
        int[] res = new int[grid[0].length];
        for (int i = 0; i < res.length; i++) {
            boolean[][][] seen = new boolean[grid.length][grid[0].length][3];
            Stack<int[]> dfs = new Stack<>();
            dfs.push(new int[] {0, i, 0});
            seen[0][0][0] = true;
            while (!dfs.isEmpty()) {
                int[] loc = dfs.pop();
                int[] nextLoc = getNextLoc(loc, grid);
                if (nextLoc[0] == grid.length) {
                    res[i] = loc[1];
                } else if (nextLoc[1] < 0 || nextLoc[1] == grid[0].length) {
                    res[i] = -1;
                } else if (!seen[nextLoc[0]][nextLoc[1]][nextLoc[2]]) {
                    dfs.push(nextLoc);
                    seen[nextLoc[0]][nextLoc[1]][nextLoc[2]] = true;
                } else {
                    res[i] = -1;
                }
            }
        }
        return res;
    }

    private static int[] getNextLoc(int[] loc, int[][] grid) {
        int[] newLoc = new int[3];
        int r = loc[0];
        int c = loc[1];
        int entry = loc[2];
        if (entry == 0) {
            //came from top
            if (grid[r][c] == 1) {
                newLoc[0] = r;
                newLoc[1] = c + 1;
                newLoc[2] = 1;
            } else {
                newLoc[0] = r;
                newLoc[1] = c - 1;
                newLoc[2] = 2;
            }
        } else if (entry == 1) {
            //came from left
            if (grid[r][c] == 1) {
                newLoc[0] = r + 1;
                newLoc[1] = c;
                newLoc[2] = 0;
            } else {
                newLoc[0] = r;
                newLoc[1] = c - 1;
                newLoc[2] = 2;
            }
        } else {
            //came from right
            if (grid[r][c] == 1) {
                newLoc[0] = r;
                newLoc[1] = c + 1;
                newLoc[2] = 1;
            } else {
                newLoc[0] = r + 1;
                newLoc[1] = c;
                newLoc[2] = 0;
            }
        }
        return newLoc;
    }

}
