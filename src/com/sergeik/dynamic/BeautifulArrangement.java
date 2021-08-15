package com.sergeik.dynamic;

/**
 *  Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is
 *  considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:
 *
 * perm[i] is divisible by i.
 * i is divisible by perm[i].
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 */
public class BeautifulArrangement {

    private static int res = 0;

    public static void main(String[] args) {
        assert 10 == solution(5);
        assert 700 == solution(10);
        assert 24679 == solution(15);
        assert 1 == solution(1);
        assert 2 == solution(2);
    }

    private static int solution(int n) {
        res = 0;
        backtrack(n, 1, new boolean[n + 1]);
        return res;
    }

    private static void backtrack(int n, int idx, boolean[] mask) {
        if (idx > n) {
            res++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!mask[i] && (idx % i == 0 || i % idx == 0)) {
                mask[i] = true;
                backtrack(n, idx + 1, mask);
                mask[i] = false;
            }
        }
    }

    private static int solutionWithBitMask(int n) {
        res = 0;
        backtrack(n, 1, 0);
        return res;
    }

    private static void backtrack(int n, int idx, int mask) {
        if (idx > n) {
            res++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                int num = i + 1;
                if (num % idx == 0 || idx % num == 0) {
                    mask |= 1 << i;
                    backtrack(n, idx + 1, mask);
                    mask &= ~(1 << i);
                }
            }
        }
    }

}
