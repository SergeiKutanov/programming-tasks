package com.sergeik.math;

public class GreatestCommonDivisor {

    public static void main(String[] args) {
        assert 2 == solution(5, new int[]{2,4,6,8,10});
        assert 1 == solution(5, new int[]{2,3,4,5,6});
    }

    private static int solution(int num, int[] arr) {

        int divisor = 0;
        for (int n : arr) {
            divisor = gcd(divisor, n);
            if (divisor == 1)
                return divisor;
        }
        return divisor;

    }

    private static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

}
