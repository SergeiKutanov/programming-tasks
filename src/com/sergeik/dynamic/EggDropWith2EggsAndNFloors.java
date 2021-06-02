package com.sergeik.dynamic;

/**
 * You are given two identical eggs and you have access to a building with n floors labeled from 1 to n.
 *
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher
 * than f will break, and any egg dropped at or below floor f will not break.
 *
 * In each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n).
 * If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
 *
 * Return the minimum number of moves that you need to determine with certainty what the value of f is.
 */
public class EggDropWith2EggsAndNFloors {

    public static void main(String[] args) {
        assert 2 == solution(2);
        assert 14 == solution(100);
    }

    private static int solution(int n) {
        //x + (X - 1) + (X - 2) + ... + 1 = n
        //x(x + 1) / 2 == n
        //x(x + 1) = 2n
        //x^2 + x - 2n = 0
        //a = 1; b = 1; c = 2n
//        int root = 1 - 8 * n;
//        double ans1 = (-1 + Math.sqrt(-root)) / 2;
//        double ans2 = (-1 - Math.sqrt(-root)) / 2;
//        double ans = ans1 > 0 ? ans1 : ans2;
//        return (int) Math.ceil(ans);

        return (int) Math.ceil(
                Math.sqrt(1 + 8 * n - 1) / 2
        );
    }

}
