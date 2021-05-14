package com.sergeik;

/**
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 */
public class SumOfTwoIntegers {

    public static void main(String[] args) {
        assert 5 == solution(2, 3);
    }

    /**
     * 2 -  10
     * 3 -  11
     * 5 - 101
     *
     *     01
     *    100
     *
     *    101
     *    0
     * @param a
     * @param b
     * @return
     */
    private static int solution(int a, int b) {
        if (b == 0) {
            return a;
        }
        return solution(a ^ b, (a & b) << 1);
    }

}
