package com.sergeik.arrays;

import java.util.*;

/**
 * We are given a list of (axis-aligned) rectangles. Each rectangle[i] = [xi1, yi1, xi2, yi2] , where (xi1, yi1)
 * are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner of the
 * ith rectangle.
 *
 * Find the total area covered by all rectangles in the plane. Since the answer may be too large, return it
 * modulo 109 + 7.
 */
public class RectangleAreaII {

    public static void main(String[] args) {
        assert 6 == coordinatesCompression(new int[][] {
                {0,0,2,2},
                {1,0,2,3},
                {1,0,3,1}
        });
    }

    private static int coordinatesCompression(int[][] rectangles) {
        Set<Integer> Xvals = new HashSet<>();
        Set<Integer> Yvals = new HashSet<>();

        for (int[] rec: rectangles) {
            Xvals.add(rec[0]);
            Xvals.add(rec[2]);
            Yvals.add(rec[1]);
            Yvals.add(rec[3]);
        }

        Integer[] imapx = Xvals.toArray(new Integer[0]);
        Arrays.sort(imapx);
        Integer[] imapy = Yvals.toArray(new Integer[0]);
        Arrays.sort(imapy);

        Map<Integer, Integer> mapx = new HashMap<>();
        Map<Integer, Integer> mapy = new HashMap<>();
        for (int i = 0; i < imapx.length; ++i)
            mapx.put(imapx[i], i);
        for (int i = 0; i < imapy.length; ++i)
            mapy.put(imapy[i], i);

        boolean[][] grid = new boolean[imapx.length][imapy.length];
        for (int[] rec: rectangles)
            for (int x = mapx.get(rec[0]); x < mapx.get(rec[2]); ++x)
                for (int y = mapy.get(rec[1]); y < mapy.get(rec[3]); ++y)
                    grid[x][y] = true;

        long ans = 0;
        for (int x = 0; x < grid.length; ++x)
            for (int y = 0; y < grid[0].length; ++y)
                if (grid[x][y])
                    ans += (long) (imapx[x+1] - imapx[x]) * (imapy[y+1] - imapy[y]);

        ans %= 1_000_000_007;
        return (int) ans;
    }

}
