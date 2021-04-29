package com.sergeik.linkedlists;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 */
public class ValidParenthesis {

    public static void main(String[] args) {
        assert solution("()");
        assert solution("()[]{}");
        assert !solution("(]");
        assert !solution("([)]");
        assert solution("{[]}");
        assert !solution("[");
    }

    private static boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.add(ch);
            } else {
                if (stack.empty())
                    return false;
                char otherCh = stack.pop();
                if (ch == ')' && otherCh != '(')
                    return false;
                if (ch == ']' && otherCh != '[')
                    return false;
                if (ch == '}' && otherCh != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

}
