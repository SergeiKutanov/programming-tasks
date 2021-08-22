package com.sergeik.stack;

import java.util.Arrays;
import java.util.Stack;

public class NumberOfVisiblePeopleInAQueue {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {4,1,1,1,0},
                solution(new int[] {5,1,2,3,10})
        );
        assert Arrays.equals(
                new int[] {3,1,2,1,1,0},
                solution(new int[] {10,6,8,5,11,9})
        );
    }

    private static int[] solution(int[] heights) {
        int[] res = new int[heights.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            int cur = heights[i];
            while (!stack.isEmpty() && cur > stack.peek()) {
                res[i]++;
                stack.pop();
            }
            if (!stack.isEmpty())
                res[i]++;
            stack.push(cur);
        }
        return res;
    }

}
