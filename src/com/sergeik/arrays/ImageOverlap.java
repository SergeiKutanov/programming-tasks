package com.sergeik.arrays;

/**
 * You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix
 * has only 0s and 1s as values.
 *
 * We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of
 * units. We then place it on top of the other image. We can then calculate the overlap by counting the number of
 * positions that have a 1 in both images.
 *
 * Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of
 * the matrix borders are erased.
 *
 * Return the largest possible overlap.
 */
public class ImageOverlap {

    public static void main(String[] args) {
        assert 3 == solution(new int[][] {
                {1,1,0},
                {0,1,0},
                {0,1,0}
        }, new int[][] {
                {0,0,0},
                {0,1,1},
                {0,0,1}
        });
    }

    private static int solution(int[][] img1, int[][] img2) {
        int maxOverlaps = 0;

        for (int yShift = 0; yShift < img1.length; ++yShift)
            for (int xShift = 0; xShift < img1.length; ++xShift) {
                // move the matrix A to the up-right and up-left directions.
                maxOverlaps = Math.max(maxOverlaps, shiftAndCount(xShift, yShift, img1, img2));
                // move the matrix B to the up-right and up-left directions, which is equivalent to moving A to the down-right and down-left directions
                maxOverlaps = Math.max(maxOverlaps, shiftAndCount(xShift, yShift, img2, img1));
            }

        return maxOverlaps;
    }

    protected static int shiftAndCount(int xShift, int yShift, int[][] M, int[][] R) {
        int leftShiftCount = 0, rightShiftCount = 0;
        int rRow = 0;
        // count the cells of ones in the overlapping zone.
        for (int mRow = yShift; mRow < M.length; ++mRow) {
            int rCol = 0;
            for (int mCol = xShift; mCol < M.length; ++mCol) {
                if (M[mRow][mCol] == 1 && M[mRow][mCol] == R[rRow][rCol])
                    leftShiftCount += 1;
                if (M[mRow][rCol] == 1 && M[mRow][rCol] == R[rRow][mCol])
                    rightShiftCount += 1;
                rCol += 1;
            }
            rRow += 1;
        }
        return Math.max(leftShiftCount, rightShiftCount);
    }

}
