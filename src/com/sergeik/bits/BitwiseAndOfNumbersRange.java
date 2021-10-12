package com.sergeik.bits;

public class BitwiseAndOfNumbersRange {

    public static void main(String[] args) {
        assert 4 == solution(5, 7);
        assert 0 == solution(0, 0);
        assert 0 == solution(1, 2147483647);
    }

    private static int solution(int left, int right) {
        //101 - 5
        //110 - 6
        //111 - 7
        //100 - 4
        int res = left;
        int bits = 0;
        int tmp = left;
        while (tmp > 0) {
            bits++;
            tmp >>= 1;
        }
        for (int i = left + 1; i <= right; i++) {
            int n = 0;
            int s = i;
            for (int j = 0; j < bits; j++) {
                n |= ((res & 1) & (s & 1)) << j;
                res >>= 1;
                s  >>= 1;
            }
            res = n;
            if (res == 0)
                return res;
        }
        return res;
    }

}
