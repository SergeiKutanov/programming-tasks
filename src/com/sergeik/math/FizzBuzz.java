package com.sergeik.math;

import com.sergeik.utils.Timer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Given an integer n, return a string array answer (1-indexed) where:
 *
 * answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 * answer[i] == "Fizz" if i is divisible by 3.
 * answer[i] == "Buzz" if i is divisible by 5.
 * answer[i] == i if non of the above conditions are true.
 */
public class FizzBuzz {

    public static void main(String[] args) {
        int n = 15;
        List<String> expected = Arrays.asList("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz");
        List<String> resultOne = Timer.runWithTimer("Solution", new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return solution(n);
            }
        });
        List<String> resultTwo = Timer.runWithTimer("Skips solution", new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return skippingSolution(n);
            }
        });
        assert compareTwoLists(expected, resultOne);
        assert compareTwoLists(expected, resultTwo);
    }

    private static boolean compareTwoLists(List<String> l1, List<String> l2) {
        if (l1.size() != l2.size())
            return false;
        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).equals(l2.get(i)))
                return false;
        }
        return true;
    }

    private static List<String> solution(int n) {
        List<String> result = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            boolean divByThree = i % 3 == 0;
            boolean divByFive = i % 5 == 0;
            if (divByFive && divByThree) {
                result.add("FizzBuzz");
            } else if (divByFive) {
                result.add("Buzz");
            } else if (divByThree) {
                result.add("Fizz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    private static List<String> skippingSolution(int n) {
        List<String> result = new LinkedList<>();
        int nextThree = 3;
        int nextFive = 5;
        for (int i = 1; i <= n; i++) {
            if (i == nextThree && i == nextFive) {
                result.add("FizzBuzz");
                nextFive += 5;
                nextThree += 3;
            } else if (i == nextThree) {
                result.add("Fizz");
                nextThree += 3;
            } else if (i == nextFive) {
                result.add("Buzz");
                nextFive += 5;
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

}
