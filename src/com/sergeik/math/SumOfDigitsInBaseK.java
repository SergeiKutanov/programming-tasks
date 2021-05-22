package com.sergeik.math;

/**
 * Given an integer n (in base 10) and a base k, return the sum of the digits of n after converting n from base 10 to base k.
 *
 * After converting, each digit should be interpreted as a base 10 number, and the sum should be returned in base 10.
 */
public class SumOfDigitsInBaseK {

    public static void main(String[] args) {
        assert 9 == solution(34, 6);
        assert 1 == solution(10, 10);
    }

    private static int solution(int n, int k) {
        int kn = 0;
        int power = 1;
        while (n > 0) {
            kn += (n % k) * power;
            n /= k;
            power *= 10;
        }
        int sum = 0;
        while (kn > 0) {
            sum += kn % 10;
            kn /= 10;
        }
        return sum;
    }

}
