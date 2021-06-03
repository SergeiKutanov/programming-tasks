package com.sergeik.greedy;

import java.util.Arrays;

/**
 * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts
 * where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly,
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 *
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided
 * in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.
 */
public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {

    public static void main(String[] args) {
        assert 4 == solution(5,4, new int[] {1,2,4}, new int[] {1,3});
        assert 9 == solution(5,4, new int[] {3}, new int[] {3});
        assert 6 == solution(5,4, new int[] {3,1}, new int[] {1});
    }

    private static int solution(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int maxH = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
        int maxV = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
        for (int i = 1; i < horizontalCuts.length; i++) {
            maxH = Math.max(
                    maxH,
                    horizontalCuts[i] - horizontalCuts[i - 1]
            );
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            maxV = Math.max(
                    maxV,
                    verticalCuts[i] - verticalCuts[i - 1]
            );
        }
        return (int)(((long) maxH * maxV) % 1000000007);
    }

}
