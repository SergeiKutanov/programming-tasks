package com.sergeik.greedy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 0-indexed binary string s and two integers minJump and maxJump.
 * In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j
 * if the following conditions are fulfilled:
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 */
public class JumpGameVII {

    public static void main(String[] args) {
        assert !solution("00111010", 3,5);
        assert solution("011010", 2, 3);
        assert !solution("01101110", 2, 3);
    }

    private static boolean solution(String s, int minJump, int maxJump) {
        if (s.charAt(s.length() - 1) == '1')
            return false;
        Queue<Integer> queue = new LinkedList<>();
        int farthest = 0;
        queue.add(0);
        while (!queue.isEmpty()) {
            int nextIndex = queue.poll();
            int start = Math.max(farthest + 1, nextIndex + minJump);
            int end = Math.min(nextIndex + maxJump, s.length() - 1);
            for (int i = start; i <= end; i++) {
                if (s.charAt(i) == '0') {
                    if (i == s.length() - 1)
                        return true;
                    queue.add(i);
                }
            }
            farthest = nextIndex + maxJump;
        }
        return false;
    }

    private static boolean solutionWindow(String s, int minJump, int maxJump) {
        int length = s.length();
        int start = 0;
        boolean[] dp = new boolean[length];
        dp[0] = true;
        for (int i = 1; i < length; i++) {
            if (i >= minJump && dp[i - minJump])
                start++;
            if (i > maxJump && dp[i - maxJump - 1])
                start--;
            dp[i] = start > 0 && s.charAt(i) == '0';
        }
        return dp[length - 1];
    }


}
