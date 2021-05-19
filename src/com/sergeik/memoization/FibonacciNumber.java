package com.sergeik.memoization;


public class FibonacciNumber {

    public static void main(String[] args) {
        assert 0 == solution(0);
        assert 144 == solution(12);
        assert 3 == solution(4);
    }

    private static int solution(int n) {
        return fib(n, new int[n + 1]);
    }

    private static int fib(int n, int[] memo) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (memo[n] > 0)
            return memo[n];
        int fib = fib(n - 2, memo) + fib(n - 1, memo);
        memo[n] = fib;
        return fib;
    }

}
