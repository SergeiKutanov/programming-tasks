package com.sergeik.stack;

import java.util.Stack;

/**
 * Given two integer arrays pushed and popped each with distinct values, return true if this could have been the
 * result of a sequence of push and pop operations on an initially empty stack, or false otherwise.
 */
public class ValidateStackSequence {

    public static void main(String[] args) {
        assert solution(new int[] {1,0}, new int[] {1,0});
        assert !solution(new int[] {1,2,3,4,5}, new int[] {4,3,5,1,2});
        assert solution(new int[] {1,2,3,4,5}, new int[] {4,5,3,2,1});
    }

    private static boolean solution(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int pushIdx = 0;
        for (int i = 0; i < popped.length; i++) {
            while (pushIdx < pushed.length && (!stack.isEmpty() && stack.peek() != popped[i]) || stack.isEmpty())
                stack.push(pushed[pushIdx++]);
            if (stack.peek() != popped[i])
                return false;
            stack.pop();
        }
        return stack.isEmpty();
    }

}
