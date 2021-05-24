package com.sergeik.sortsearch;

import java.util.Stack;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        assert 10 == solution(new int[]{2,1,5,6,2,3});
    }

    private static int solution(int[] heights) {
        int max = 0;
        int topOfStack = 0;
        int areaWithTop = 0;
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i++);
            } else {
                topOfStack = stack.pop();
                areaWithTop = heights[topOfStack] * (stack.isEmpty() ? i : i - 1 - stack.peek());
                max = Math.max(areaWithTop, max);
            }
        }

        while (!stack.isEmpty()) {
            topOfStack = stack.pop();
            areaWithTop = heights[topOfStack] + (stack.isEmpty() ? i : i - 1 - stack.peek());
            max = Math.max(areaWithTop, max);
        }

        return max;
    }

}
