package com.sergeik.bits;

public class ComplementOfBase10Integer {

    public static void main(String[] args) {
        assert 5 == solution(10);
        assert 1 == solution(0);
        assert 2 == solution(5);
    }

    private static int solution(int n) {
        if (n == 0)
            return 1;
        int res = ~n, mask = 0;
        while (n > 0) {
            mask <<= 1;
            mask += 1;
            n >>= 1;
        }
        return res & mask;
    }

    private static int bitSolution(int n) {
        if (n == 0)
            return 1;
        int res = 0, order = 0;
        while (n > 0) {
            res |= ((n & 1) == 0) ? 1 << order : 0;
            n >>= 1;
            order++;
        }
        return res;
    }

}
