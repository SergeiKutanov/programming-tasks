package com.sergeik.dynamic;

/**
 * Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations
 * do not contain consecutive ones.
 */
public class NonNegativeIntegersWithoutConsecutiveOnes {

    private static int res = 0;

    public static void main(String[] args) {
        assert 4 == solution(4);
        assert 5 == solution(5);
        assert 3 == solution(2);
        assert 13 == solution(31);
        assert 4181 == solution(100000);
    }

    private static int solution(int n) {
        int size = Integer.toBinaryString(n).length();
        int[] dp = new int[size + 1];
        dp[0] = 1; dp[1] = 2;
        for (int i = 2; i <= size; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int i = size, sum = 0, prevBit = 0;
        while (i >= 0) {
            if ((n & (1 << i)) != 0) {
                sum += dp[i];
                if (prevBit == 1) {
                    sum--;
                    break;
                }
                prevBit = 1;
            } else {
                prevBit = 0;
            }
            i--;
        }

        return sum + 1;
    }

    private static int bruteSolution(int n) {
        res = 1;
        dfs(n, 1);
        System.out.println(n + ": " + res);
        return res;
    }

    private static void dfs(int n, int num) {
        if (num > n)
            return;
        res++;
        if ((num & 1) == 0) {
            dfs(n, (num << 1) + 1);
        }
        dfs(n, num << 1);
    }

}
