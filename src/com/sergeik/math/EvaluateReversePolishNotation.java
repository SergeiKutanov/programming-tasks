package com.sergeik.math;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate 
 * to a result, and there will not be any division by zero operation.
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        /*
        ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        = ((10 * (6 / (12 * -11))) + 17) + 5
        = ((10 * (6 / -132)) + 17) + 5
        = ((10 * 0) + 17) + 5
        = (0 + 17) + 5
        = 17 + 5
        = 22
         */
        assert 22 == solution(new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"});
        assert 9 == solution(new String[]{"2","1","+","3","*"});
        assert 6 == solution(new String[]{"4","13","5","/","+"});
    }

    private static int solution(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int x,y,z;
        for (String str: tokens) {
            switch (str) {
                case "+":
                    y = stack.pop();
                    x = stack.pop();
                    z = x + y;
                    stack.push(z);
                    break;
                case "-":
                    y = stack.pop();
                    x = stack.pop();
                    z = x - y;
                    stack.push(z);
                    break;
                case "*":
                    y = stack.pop();
                    x = stack.pop();
                    z = x * y;
                    stack.push(z);
                    break;
                case "/":
                    y = stack.pop();
                    x = stack.pop();
                    z = x / y;
                    stack.push(z);
                    break;
                default:
                    Integer num = Integer.valueOf(str);
                    stack.push(num);
            }
        }
        return stack.pop();
    }

}
