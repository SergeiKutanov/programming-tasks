package com.sergeik.sortsearch;

/**
 * You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the
 * ith row has exactly i coins. The last row of the staircase may be incomplete.
 *
 * Given the integer n, return the number of complete rows of the staircase you will build.
 */
public class ArrangeCoins {

    public static void main(String[] args) {
        assert 1 == solution(1);
        assert 65535 == solution(Integer.MAX_VALUE);
        assert 1570 == solution(1234567);
        assert 2 == solution(5);
        assert 3 == solution(8);
    }

    private static int mathSolution(int n) {
        //k*(k + 1) / 2 = n
        //k*(k+1) = n * 2
        //k^2 + k = 2n
        //k^2=2n - k
        //k^2 - 2n + k = 0
        //k^2 + k = 10
        //k^2 + k -10 = 0
        //x = (-1 + root(1 - 4 * (-10))) / 2

        double root = Math.sqrt(1 - 4 * (-2.0 * n));
        Double res = (-1 + root) / 2;
        return res.intValue();
    }

    private static int solution(int n) {
        long min = 1, max = n;
        while (min <= max) {
            long mid = (max - min) / 2 + min;
            long coins = mid * (mid + 1) / 2;
            if (coins < n)
                min = mid + 1;
            else if (coins > n)
                max = mid - 1;
            else
                return (int)mid;
        }
        return (int)max;
    }

}
