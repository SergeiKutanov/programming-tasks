package com.sergeik.math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example,
 * truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed
 * integer range: [−231, 231 − 1]. For this problem, assume that your function returns 231 − 1
 * when the division result overflows.
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        assert 3 == solution(10, 3);
        assert -2 == solution(7, -3);
    }

    private static int solution(int dividend, int divisor) {
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        int quotient = 0;
        while (dividend - divisor > 0) {
            dividend -= divisor;
            quotient++;
        }
        return sign * quotient;
    }

    private static int solutionBit(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        if (dividend > 0 && divisor > 0)
            return divideHelper(-dividend, -divisor);
        else if (dividend > 0)
            return -divideHelper(-dividend, divisor);
        else if (divisor > 0)
            return -divideHelper(dividend, -divisor);
        else
            return divideHelper(dividend, divisor);
    }

    private static int divideHelper(int dividend, int divisor){
        // base case
        if (divisor < dividend) return 0;
        // get highest digit of divisor
        int cur = 0;
        int res = 0;
        while ((divisor << cur) >= dividend && divisor << cur < 0 && cur < 31)
            cur++;
        res = dividend - (divisor << cur - 1);
        if (res > divisor)
            return 1 << cur - 1;
        return (1 << cur - 1) + solution(res, divisor);
    }

}
