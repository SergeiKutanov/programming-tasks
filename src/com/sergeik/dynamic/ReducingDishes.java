package com.sergeik.dynamic;

import java.util.Arrays;

/**
 * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
 *
 * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes
 * multiplied by its satisfaction level  i.e.  time[i]*satisfaction[i]
 *
 * Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.
 *
 * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
 */
public class ReducingDishes {

    public static void main(String[] args) {
        assert 7281 == greedySolution(new int[] {76,83,55,-36,-8,40,-60,-72,27,-32,37,1,77,24,-46,-26,20,-89,-35,58,-7});
        assert 35 == greedySolution(new int[]{-2,5,-1,0,3,-3});
        assert 0 == greedySolution(new int[] {-1,-4,-5});
        assert 20 == greedySolution(new int[] {4,3,2});
        assert 14 == greedySolution(new int[] {-1, -8, 0, 5, -9});
    }

    private static int greedySolution(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int res = 0, total = 0;
        for (int i = satisfaction.length - 1; i >= 0 && satisfaction[i] > -total; i--) {
            total += satisfaction[i];
            res += total;
        }
        return res;
    }

    private static int optimizedBruteForceSolution(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int max = 0;
        for (int i = 1; i <= satisfaction.length; i++) {
            int r = satisfaction.length - 1, col = i;
            int cSum = 0;
            while (r >= 0 && col > 0) {
                cSum += satisfaction[r--] * col--;
            }
            if (cSum < max)
                return max;
            max = cSum;
        }

        return max;
    }

}
