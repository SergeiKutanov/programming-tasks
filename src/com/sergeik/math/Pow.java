package com.sergeik.math;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 */
public class Pow {

    public static void main(String[] args) {
        assert 0 == Double.compare(1024.0, solution(2, 10));
        assert 0 == Double.compare(0.25, solution(2, -2));
    }

    private static double solution(double x, int n) {
        if(n == 0)
            return 1;
        if(n<0){
            return 1 / x * solution(1 / x, -(n + 1));
        }
        return (n % 2 == 0) ? solution(x * x, n / 2) : x * solution(x * x, n / 2);
    }

}
