package com.sergeik.math;

/**
 * Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.
 *
 * He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more
 * than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
 * Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.
 */
public class CalculateMoneyInLeetcodeBank {

    public static void main(String[] args) {
        assert 96 == solution(20);
        assert 37 == solution(10);
        assert 10 == solution(4);
    }

    private static int solution(int n) {
        int res = 0;
        int week = 1;
        for (int i = 0; i <= n; i += 7) {
            int lastDay = Math.min(i + 7, n);
            int start = week - 1;
            int end = start + lastDay - i;
            res += (end * (end + 1) / 2) - (start * (start + 1) / 2);
            week++;
        }
        return res;
    }

}
