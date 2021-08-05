package com.sergeik.stack;

import java.util.Stack;

/**
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 */
public class ScoreOfParenthesis {

    public static void main(String[] args) {
        assert 2 == solution("()()");
        assert 2 == solution("(())");
        assert 1 == solution("()");
        assert 6 == solution("(()(()))");
    }

    private static int solution(String s) {
        Stack<Character> stack = new Stack<>();
        int res = 0, d = 0;
        for (char ch: s.toCharArray()) {
            if (ch == '(') {
                d++;
                stack.push(ch);
            } else {
                int p = d;
                if (p >= 0 && !stack.isEmpty())
                    res += 1 << (p - 1);
                d--;
                while (!stack.isEmpty() && p-- > 0)
                    stack.pop();
            }
        }
        return res;
    }

}
