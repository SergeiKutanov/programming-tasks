package com.sergeik.math;

/**
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 */
public class PowerOfThree {

    public static void main(String[] args) {
        assert recSolution(1);
        assert recSolution(27);
        assert !recSolution(0);
        assert recSolution(9);
        assert !recSolution(45);
        assert !recSolution(-1);

        assert itSolution(27);
        assert itSolution(1);
        assert !itSolution(0);
        assert itSolution(9);
        assert !itSolution(45);
        assert !itSolution(-1);

        assert strBaseSolution(27);
        assert strBaseSolution(1);
        assert !strBaseSolution(0);
        assert strBaseSolution(9);
        assert !strBaseSolution(45);
        assert !strBaseSolution(-1);

        assert maxIntegerValueSolution(27);
        assert maxIntegerValueSolution(1);
        assert !maxIntegerValueSolution(0);
        assert maxIntegerValueSolution(9);
        assert !maxIntegerValueSolution(45);
        assert !maxIntegerValueSolution(-1);
    }

    private static boolean recSolution(int n) {
        if (n == 1)
            return true;
        return isPowerOf(n, 3);
    }

    /**
     * Knowing the limitation of n, we can now deduce that the maximum value of n
     * that is also a power of three is 1162261467
     * @param n
     * @return
     */
    private static boolean maxIntegerValueSolution(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    private static boolean strBaseSolution(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    private static boolean itSolution(int n) {
        if (n < 1)
            return false;
        while ((n % 3) == 0) {
            n /= 3;
        }
        return n == 1;
    }



    private static boolean isPowerOf(int n, int powerOf) {
        if (n <= 0 || (n % powerOf) != 0)
            return false;
        int remainder = n / powerOf;
        if (remainder == 1)
            return true;
        return isPowerOf(remainder, powerOf);
    }

}
