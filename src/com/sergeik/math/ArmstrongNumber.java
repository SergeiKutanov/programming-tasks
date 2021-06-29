package com.sergeik.math;

/**
 * Given an integer n, return true if and only if it is an Armstrong number.
 *
 * The k-digit number n is an Armstrong number if and only if the kth power of each digit sums to n.
 */
public class ArmstrongNumber {

    public static void main(String[] args) {
        assert !solution(123);
        assert solution(153);
    }

    private static boolean solution(int n) {
        int power = 0;
        int d = n;
        while (d > 0) {
            power++;
            d /= 10;
        }
        int sum = 0;
        d = n;
        while (d > 0) {
            int digit = d % 10;
            sum += Math.pow(digit, power);
            d /= 10;
            if (sum > n)
                return false;
        }
        return sum == n;
    }

}
