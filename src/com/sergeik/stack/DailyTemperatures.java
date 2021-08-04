package com.sergeik.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that
 * answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is
 * no future day for which this is possible, keep answer[i] == 0 instead.
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {1,1,4,2,1,1,0,0}, solution(new int[] {73,74,75,71,69,72,76,73}));
    }

    private static int[] solution(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                res[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

}
