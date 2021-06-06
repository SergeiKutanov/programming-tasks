package com.sergeik.backtracking;

import java.util.Arrays;

/**
 * Given an integer n, find a sequence that satisfies all of the following:
 *
 * The integer 1 occurs once in the sequence.
 * Each integer between 2 and n occurs twice in the sequence.
 * For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
 * The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.
 *
 * Return the lexicographically largest sequence. It is guaranteed that under the given constraints,
 * there is always a solution.
 *
 * A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position
 * where a and b differ, sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0]
 * is lexicographically larger than [0,1,5,6] because the first position they differ is at the third number,
 * and 9 is greater than 5.
 */
public class ConstructTheLexicographicallyLargestValidSequence {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {5,3,1,4,3,5,2,4,2},
                solution(5)
        );
    }

    private static int[] solution(int n) {
        int len = (n - 1) * 2 + 1;
        int[] max = new int[len];
        backtrack(max, n, 0, new boolean[n + 1]);
        return max;
    }

    private static boolean backtrack(int[] res, int n, int start, boolean[] visited) {
        if (start == res.length)
            return true;
        if (res[start] != 0)
            return backtrack(res, n, start + 1, visited);
        for (int i = n; i >= 1; i--) {
            if (visited[i]) continue;
            visited[i] = true;
            res[start] = i;
            if (i == 1 && backtrack(res, n, start + 1, visited))
                    return true;
            else if (start + i < res.length && res[start + i] == 0) {
                res[start + i] = i;
                if (backtrack(res, n, start + 1, visited))
                    return true;
                res[start + i] = 0;

            }
            visited[i] = false;
            res[start] = 0;
        }
        return false;
    }

}
