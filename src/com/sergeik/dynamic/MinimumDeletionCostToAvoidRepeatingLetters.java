package com.sergeik.dynamic;

import java.util.Stack;

/**
 * Given a string s and an array of integers cost where cost[i] is the cost of deleting the ith character in s.
 *
 * Return the minimum cost of deletions such that there are no two identical letters next to each other.
 *
 * Notice that you will delete the chosen characters at the same time, in other words, after deleting a character,
 * the costs of deleting other characters will not change.
 */
public class MinimumDeletionCostToAvoidRepeatingLetters {

    public static void main(String[] args) {
        assert 26 == solution("aaabbbabbbb", new int[] {3,5,10,7,5,3,5,5,4,8,1});
        assert 2 == solution("aabaa", new int[] {1,2,3,4,1});
        assert 3 == solution("abaac", new int[] {1,2,3,4,5});
        assert 0 == solution("abc", new int[] {1,2,3});
    }

    private static int solution(String s, int[] cost) {
        int maxCost = cost[0], sumCost = cost[0], res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                res += sumCost - maxCost;
                sumCost = 0; maxCost = 0;
            }
            sumCost += cost[i];
            maxCost = Math.max(maxCost, cost[i]);
        }
        res += sumCost - maxCost;
        return res;
    }

    private static int stackSolution(String s, int[] cost) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(stack.peek())) {
                if (cost[stack.peek()] > cost[i]) {
                    res += cost[i];
                } else {
                    res += cost[stack.peek()];
                    stack.pop();
                    stack.push(i);
                }
            } else
                stack.push(i);
        }
        return res;
    }

}
