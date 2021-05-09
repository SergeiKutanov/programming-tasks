package com.sergeik.math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 */
public class FactorialTrailingZeros {

    public static void main(String[] args) {
        assert 0 == solution(3);
        assert 1 == solution(5);
        assert 1 == solution(7);
        assert 2 == solution(10);
    }

    /**
     * Each 5 factor produces a new trailing zero
     * @param n
     * @return
     */
    private static int solution(int n) {
        int count = 0;
        for (int i = 5; n / i >= 1; i *=5) {
            count += n / i;
        }
        return count;
    }

}
