package com.sergeik.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive
 * meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 */
public class AsteroidCollision {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {-2, 1},
                solution(new int[]{-2,1,1,-1})
        );
        assert Arrays.equals(
                new int[]{-2,-1,1,2},
                solution(new int[] {-2,-1,1,2})
        );
        assert Arrays.equals(
                new int[] {10},
                solution(new int[]{10,2,-5})
        );
        assert Arrays.equals(
                new int[0],
                solution(new int[]{8,-8})
        );
        assert Arrays.equals(
                new int[] {5,10},
                solution(new int[] {5,10,-5})
        );
    }

    private static int[] solution(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int n: asteroids) {
            boolean asteroidDestroyed = false;
            while (!stack.isEmpty() && n < 0 && stack.peek() >= 0) {
                int left = stack.pop();
                int leftAbs = Math.abs(left);
                int rightAbs = Math.abs(n);
                if (leftAbs > rightAbs) {
                    n = left;
                } else if (leftAbs == rightAbs) {
                    asteroidDestroyed = true;
                    break;
                }
            }
            if (!asteroidDestroyed)
                stack.push(n);
        }

        int[] res = new int[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.isEmpty()) {
            res[idx--] = stack.pop();
        }
        return res;
    }

}
