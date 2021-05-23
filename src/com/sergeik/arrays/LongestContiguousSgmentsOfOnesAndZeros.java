package com.sergeik.arrays;

/**
 * Given a binary string s, return true if the longest contiguous segment of 1s is strictly longer than
 * the longest contiguous segment of 0s in s. Return false otherwise.
 *
 * For example, in s = "110100010" the longest contiguous segment of 1s has length 2, and the longest contiguous
 * segment of 0s has length 3.
 * Note that if there are no 0s, then the longest contiguous segment of 0s is considered to have length 0.
 * The same applies if there are no 1s.
 */
public class LongestContiguousSgmentsOfOnesAndZeros {

    public static void main(String[] args) {
        assert solution("110011111010000");
        assert !solution("11001111010000");
        assert solution("11111");
        assert !solution("0000");
    }

    private static boolean solution(String s) {
        int ones = 0;
        int zeros = 0;
        int maxOnes = 0;
        int maxZeros = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones++;
                if (zeros > 0) {
                    maxZeros = Math.max(maxZeros, zeros);
                    zeros = 0;
                }
            } else {
                zeros++;
                if (ones > 0) {
                    maxOnes = Math.max(maxOnes, ones);
                    ones = 0;
                }
            }
        }

        maxOnes = Math.max(maxOnes, ones);
        maxZeros = Math.max(maxZeros, zeros);

        return maxOnes > maxZeros;
    }

}
