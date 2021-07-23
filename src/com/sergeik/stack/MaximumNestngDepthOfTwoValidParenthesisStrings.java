package com.sergeik.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:
 *
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 *
 * depth("") = 0
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * For example,  "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()"
 * are not VPS's.
 *
 *
 *
 * Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's
 * (and A.length + B.length = seq.length).
 *
 * Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.
 *
 * Return an answer array (of length seq.length) that encodes such a choice of A and B:
 * answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers
 * may exist, you may return any of them.
 */
public class MaximumNestngDepthOfTwoValidParenthesisStrings {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {0,0,0,1,1,0,0,0}, bitSolution("()(())()"));
        assert Arrays.equals(new int[] {0,1,1,1,1,0}, bitSolution("(()())"));
    }

    private static int[] bitSolution(String seq) {
        int[] res = new int[seq.length()];
        for (int i = 0; i < seq.length(); i++)
            res[i] = seq.charAt(i) == '(' ? i & 1 : (1 - i & 1);
        return res;
    }

    private static int[] shortSolution(String seq) {
        int[] res = new int[seq.length()];
        boolean a = true;
        for (int i = 0; i < seq.length(); i++) {
            res[i] = seq.charAt(i) == '(' ? a ? 0 : 1 : a ? 1: 0;
            a = !a;
        }
        return res;
    }

    private static int[] solution(String seq) {
        int[] res = new int[seq.length()];
        boolean a = true;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                res[i] = a ? 0 : 1;
            } else {
                res[i] = a ? 1: 0;
            }
            a = !a;
        }
        return res;
    }

    private static int[] stackSolution(String seq) {
        int[] res = new int[seq.length()];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                res[i] = stack.peek();
                stack.push(stack.peek() == 0 ? 1 : 0);
            } else {
                res[i] = stack.pop() == 0 ? 1 : 0;
            }
        }

        return res;
    }

}
