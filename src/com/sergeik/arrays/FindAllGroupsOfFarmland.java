package com.sergeik.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1
 * represents a hectare of farmland.
 *
 * To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland.
 * These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not
 * four-directionally adjacent to another farmland in a different group.
 *
 * land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right
 * corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of
 * farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is
 * represented by the 4-length array [r1, c1, r2, c2].
 *
 * Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no
 * groups of farmland, return an empty array. You may return the answer in any order.
 */
public class FindAllGroupsOfFarmland {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[][] {
                        {0,0,0,0},
                        {1,1,2,2}
                },
                solution(new int[][] {
                        {1,0,0},
                        {0,1,1},
                        {0,1,1}
                })
        );
    }

    private static int[][] solution(int[][] land) {
        List<int[]> res = new ArrayList<>();
        boolean[][] seen = new boolean[land.length][land[0].length];
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] == 0 || seen[i][j])
                    continue;
                int[] coor = new int[4];
                coor[0] = i; coor[1] = j;
                int x = i + 1, y = j + 1;
                while (y < land[x].length && land[i][y] == 1)
                    y++;
                while (x < land.length && land[x][j] == 1)
                    x++;
                coor[2] = x - 1; coor[3] = y - 1;
                for (int k = i; k < x; k++)
                    for (int h = j; h < y; h++)
                        seen[k][h] = true;
                res.add(coor);
            }
        }
        return res.toArray(new int[res.size()][4]);
    }

}
