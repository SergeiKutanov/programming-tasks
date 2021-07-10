package com.sergeik.dynamic;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse
 * of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit
 * from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded
 * messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding
 * any of the encoded messages it can represent.
 *
 * Given a string s containing digits and the '*' character, return the number of ways to decode it.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */
public class DecodeWaysII {

    public static void main(String[] args) {
        assert 1 == solution("204");
        assert 15 == solution("2*");
    }

    private static int solution(String s) {
        return (int) ways(s, s.length() - 1, new Long[s.length()]);
    }

    private static long ways(String s, int idx, Long[] memo) {
        int mod = 1000000007;
        if (idx < 0)
            return 1;
        if (memo[idx] != null)
            return memo[idx];
        if (s.charAt(idx) == '*') {
            long res = 9 * ways(s, idx - 1, memo) % mod;
            if (idx > 0 && s.charAt(idx - 1) == '1') {
                res = (res + 9 * ways(s, idx - 2, memo)) % mod;
            } else if (idx > 0 && s.charAt(idx - 1) == '2') {
                res = (res + 6 * ways(s, idx - 2, memo)) % mod;
            } else if (idx > 0 && s.charAt(idx - 1) == '*') {
                res = (res + 15 * ways(s, idx - 2, memo)) % mod;
            }
            memo[idx] = res;
            return res;
        }
        long res = s.charAt(idx) == '0' ? 0 : ways(s, idx - 1, memo);
        if (idx > 0 && s.charAt(idx - 1) == '1') {
            res = (res + ways(s, idx - 2, memo)) % mod;
        } else if (idx > 0 && s.charAt(idx - 1) == '2' && s.charAt(idx) <= '6') {
            res = (res + ways(s, idx - 2, memo)) % mod;
        } else if (idx > 0 && s.charAt(idx - 1) == '*')
            res = (res + (s.charAt(idx) <= '6' ? 2 : 1) * ways(s, idx - 2, memo)) % mod;
        memo[idx] = res;
        return memo[idx];
    }

}
