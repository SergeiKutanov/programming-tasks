package com.sergeik.strings;

import java.util.Stack;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 */
public class BasicCalculator {

    public static void main(String[] args) {

        assert -1 == solution("1-1-1");
        assert 5 == solution(" 3+5 / 2 ");
        assert 1 == solution("3/2 ");
        assert 7 == solution("3+2*2");
        assert 8 == solution("3*2+2");
    }

    private static int solution(String s) {
        int len = s.length();
        Stack<Integer> nums = new Stack<>();
        char currentSign = '+';
        int num = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            }
            if ((!Character.isDigit(ch) && ' ' != ch) || i == len - 1) {
                switch (currentSign) {
                    case '-':
                        nums.push(-num);
                        break;
                    case '+':
                        nums.push(num);
                        break;
                    case '*':
                        nums.push(nums.pop() * num);
                        break;
                    case '/':
                        nums.push(nums.pop() / num);
                        break;
                }
                currentSign = ch;
                num = 0;
            }
        }

        int res = 0;
        while (!nums.isEmpty())
            res += nums.pop();

        return res;
    }

}
