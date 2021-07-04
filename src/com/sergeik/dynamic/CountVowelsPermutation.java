package com.sergeik.dynamic;

/**
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 *
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */
public class CountVowelsPermutation {

    public static void main(String[] args) {
        assert 68 == solution(5);
    }

    private static int solution(int n) {
        int mod = 1000000007;
        long[] aCount = new long[n];
        long[] eCount = new long[n];
        long[] iCount = new long[n];
        long[] oCount = new long[n];
        long[] uCount = new long[n];

        aCount[0] = 1L; eCount[0] = 1L; iCount[0] = 1L;
        oCount[0] = 1L; uCount[0] = 1L;

        for (int i = 1; i < n; i++) {
            int prev = i - 1;
            aCount[i] = (eCount[prev] + iCount[prev] + uCount[prev]) % mod;
            eCount[i] = (aCount[prev] + iCount[prev]) % mod;
            iCount[i] = (eCount[prev] + oCount[prev]) % mod;
            oCount[i] = (iCount[prev]);
            uCount[i] = (iCount[prev] + oCount[prev]) % mod;
        }

        long res = 0L;
        int idx = n - 1;
        res += (aCount[idx] + eCount[idx] + iCount[idx] + oCount[idx] + uCount[idx]) % mod;
        return (int) res;
    }

}
