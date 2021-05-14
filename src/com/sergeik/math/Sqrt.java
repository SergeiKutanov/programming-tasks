package com.sergeik.math;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and only the 
 * integer part of the result is returned.
 */
public class Sqrt {

    public static void main(String[] args) {
        assert 2 == solution(4);
        assert 2 == solution(8);
    }

    private static int solution(int x) {
        if (x == 0)
            return 0;
        int left = 1;
        int right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }

}
