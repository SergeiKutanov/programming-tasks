package com.sergeik.greedy;

import java.util.Arrays;

/**
 * There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:
 *
 * In each step, you will choose any 3 piles of coins (not necessarily consecutive).
 * Of your choice, Alice will pick the pile with the maximum number of coins.
 * You will pick the next pile with maximum number of coins.
 * Your friend Bob will pick the last pile.
 * Repeat until there are no more piles of coins.
 * Given an array of integers piles where piles[i] is the number of coins in the ith pile.
 *
 * Return the maximum number of coins which you can have.
 */
public class MaximumNumberOfCoinsYouCanGet {

    public static void main(String[] args) {
        assert 18 == solution(new int[] {9,8,7,6,5,1,2,3,4});
        assert 9 == solution(new int[]{2,4,1,2,7,8});
    }

    private static int solution(int[] piles) {
        Arrays.sort(piles);
        int left = 0, right = piles.length - 1;
        int res = 0;
        while (left < right) {
            left++;
            right--;
            res += piles[right--];
        }
        return res;
    }

}
