package com.sergeik.sortsearch;

public class FirstBadVersion {

    private static int currentBad = 0;

    public static void main(String[] args) {

        currentBad = 1702766719;
        int res = solution(2126753390);
        assert currentBad == res;
        res = iterativeSolution(2126753390);
        assert currentBad == res;


        currentBad = 4;
        res = solution(5);
        assert currentBad == res;
        res = iterativeSolution(5);
        assert currentBad == res;

        currentBad = 1;
        res = solution(1);
        assert currentBad == res;
        res = iterativeSolution(1);
        assert currentBad == res;
    }

    private static int iterativeSolution(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end-start) / 2;
            if (!isBadVersion(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    private static int solution(int n) {
        return divideAndConquer(0, n);
    }

    private static int divideAndConquer(int min, int max) {
        int middle = min + (max - min) / 2;
        boolean isBad = isBadVersion(middle);
        if (!isBad) {
            return divideAndConquer(middle + 1, max);
        } else {
            if (middle == 0)
                return middle;
            if (!isBadVersion(middle - 1))
                return middle;
            return divideAndConquer(min, middle - 1);
        }
    }

    private static boolean isBadVersion(int n) {
        return currentBad <= n;
    }

}
