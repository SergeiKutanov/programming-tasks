package com.sergeik.arrays;

/**
 * There are several cards arranged in a row, and each card has an associated number of points
 * The points are given in the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 */
public class MaxPointsFromCards {

    public static void main(String[] args) {
        assert 536 == solution(new int[]{96,90,41,82,39,74,64,50,30}, 8);
        assert 12 == solution(new int[]{1,2,3,4,5,6,1}, 3);
        assert 4 == solution(new int[]{2,2,2}, 2);
        assert 55 == solution(new int[]{9,7,7,9,7,7,9}, 7);
    }

    /**
     * Keep a window of size n - k over the array. The answer is max(answer, total_pts - sumOfCurrentWindow)
     * @param cardPoints
     * @param k
     * @return
     */
    private static int solution(int[] cardPoints, int k) {
        int max = 0;
        int windowSize = cardPoints.length - k;
        int maxTotal = 0;
        for (int c: cardPoints)
            maxTotal += c;

        if (windowSize == 0)
            return maxTotal;

        int windowSum = 0;
        for (int i = 0; i < windowSize - 1; i++)
            windowSum += cardPoints[i];
        for (int i = 0; i <= k; i++) {
            if (i > 0) {
                windowSum -= cardPoints[i - 1];
            }
            windowSum += cardPoints[windowSize + i - 1];
            max = Math.max(max, maxTotal - windowSum);
        }
        return max;
    }



}
