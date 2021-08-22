package com.sergeik.strings;

public class CountBinarySubstrings {

    public static void main(String[] args) {
        assert 6 == groupSolution("00110011");
        assert 4 == solution("10101");
    }

    private static int groupSolution(String s) {
        int t = 0;
        int[] groups = new int[s.length()];
        groups[t] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1))
                groups[++t] = 1;
            else
                groups[t]++;
        }
        int res = 0;
        for (int i = 1; i <=t; i++) {
            res += Math.min(groups[i - 1], groups[i]);
        }
        return res;
    }

    private static int solution(String s) {
        int res = 0;
        int idx = 1;
        while (idx < s.length()) {
            int left = idx - 1, right = idx;
            char lCh = s.charAt(left), rCh = s.charAt(right);
            if (lCh != rCh) {
                while (left >= 0 && right < s.length() && s.charAt(left) == lCh && s.charAt(right) == rCh) {
                    res++;
                    left--;
                    right++;
                }
            }
            idx++;
        }
        return res;
    }

}
