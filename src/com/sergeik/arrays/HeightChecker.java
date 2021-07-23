package com.sergeik.arrays;

/**
 * A school is trying to take an annual photo of all the students. The students are asked to stand in a single file
 * line in non-decreasing order by height. Let this ordering be represented by the integer array expected where
 * expected[i] is the expected height of the ith student in line.
 *
 * You are given an integer array heights representing the current order that the students are standing in.
 * Each heights[i] is the height of the ith student in line (0-indexed).
 *
 * Return the number of indices where heights[i] != expected[i].
 */
public class HeightChecker {

    public static void main(String[] args) {
        assert 5 == solution(new int[]{5,1,2,3,4});
        assert 3 == solution(new int[] {1,1,4,2,1,3});
    }

    private static int solution(int[] heights) {
        int[] count = new int[101];
        for (int n: heights)
            count[n]++;
        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int minIndex = count[heights[i] - 1];
            int maxIndex = count[heights[i]] - 1;
            res += i >= minIndex && i <= maxIndex ? 0 : 1;
        }

        return res;
    }

}
