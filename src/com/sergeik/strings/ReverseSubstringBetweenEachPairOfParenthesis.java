package com.sergeik.strings;

import java.util.Stack;

/**
 * You are given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 */
public class ReverseSubstringBetweenEachPairOfParenthesis {

    public static void main(String[] args) {
        assert "apmnolkjihgfedcbq".equals(solution("a(bcdefghijkl(mno)p)q"));
        assert "iloveu".equals(solution("(u(love)i)"));
        assert "leetcode".equals(solution("(ed(et(oc))el)"));
    }

    private static String solution(String s) {
        int[] pairs = new int[s.length()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else if (s.charAt(i) == ')') {
                int openIdx = stack.pop();
                pairs[openIdx] = i;
                pairs[i] = openIdx;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0, d = 1; i < s.length(); i += d) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pairs[i];
                d = -d;
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

}
