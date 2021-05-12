package com.sergeik.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        List<String> expected = Arrays.asList("((()))","(()())","(())()","()(())","()()()");
        List<String> res = solution(3);
        assert expected.equals(res);
    }

    private static List<String> solution(int n) {
        List<String> res = new LinkedList<>();
        getCombinations(res, "", 0, 0, n);
        return res;
    }

    private static void getCombinations(List<String> list, String str, int open, int close, int max) {
       if (str.length() == max * 2) {
           list.add(str);
           return;
       }
       if (open < max)
           getCombinations(list, str + "(", open + 1, close, max);
       if (close < open)
           getCombinations(list, str + ")", open, close + 1, max);
    }
}
