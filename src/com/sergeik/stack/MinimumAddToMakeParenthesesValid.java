package com.sergeik.stack;

import java.util.Stack;

/**
 * Given a string s of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any
 * positions ) so that the resulting parentheses string is valid.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 */
public class MinimumAddToMakeParenthesesValid {

    public static void main(String[] args) {
        assert 4 == solution("()))((");
        assert 0 == solution("()");
        assert 1 == solution("())");
        assert 3 == solution("(((");
    }

    private static int solution(String s) {
        int res = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                count++;
            else {
                if (count == 0)
                    res++;
                else
                    count--;
            }

        }
        return res + Math.abs(count);
    }

    private static int stackSolution(String s) {
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                stack.push(ch);
            else {
                if (!stack.isEmpty() && stack.peek() == '(')
                    stack.pop();
                else
                    ans++;
            }
        }
        return ans + stack.size();
    }

}
