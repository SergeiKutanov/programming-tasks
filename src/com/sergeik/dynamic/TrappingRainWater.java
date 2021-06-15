package com.sergeik.dynamic;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        assert 9 == solution(new int[]{4,2,0,3,2,5});
        assert 6 == solution(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }

    private static int solution(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int idx = stack.pop();
                if (stack.isEmpty())
                    break;
                int dist = i - stack.peek() - 1;
                int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[idx];
                res += dist * boundedHeight;
            }
            stack.push(i);
        }
        return res;
    }

    private static int dpSolution(int[] height) {
        int res = 0;
        int[] leftMax = new int[height.length + 1];
        int[] rightMax = new int[height.length + 1];
        for (int i = 0; i < height.length; i++) {
            leftMax[i + 1] = Math.max(leftMax[i], height[i]);
        }
        for (int i = height.length - 1; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        for (int i = 0; i < height.length; i++) {
            int leftBorder = leftMax[i];
            int rightBorder = rightMax[i];
            int val = height[i];
            if (val < leftBorder && val < rightBorder)
                res += Math.min(leftBorder, rightBorder) - val;
        }

        return res;
    }

}
