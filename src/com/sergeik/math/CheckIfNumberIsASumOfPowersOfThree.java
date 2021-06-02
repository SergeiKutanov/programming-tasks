package com.sergeik.math;

/**
 * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three.
 * Otherwise, return false.
 *
 * An integer y is a power of three if there exists an integer x such that y == 3x.
 */
public class CheckIfNumberIsASumOfPowersOfThree {

    public static void main(String[] args) {

        assert mathSolution(91);
        assert !mathSolution(21);

        assert solution(91);
        assert !solution(21);
    }

    private static boolean mathSolution(int n) {
        while (n > 0 && n % 3 != 2) {
            n = n % 3 == 1 ? n - 1 : n / 3;
        }
        return n == 0;
    }

    private static boolean solution(int n) {
        String s = Integer.toString(n, 3);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '2')
                return false;
        }
        return true;
    }

}
