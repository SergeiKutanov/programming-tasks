package com.sergeik.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly
 * in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 */
public class HappyNumber {

    public static void main(String[] args) {
        assert !solution(2);
        assert solution(19);
    }

    private static boolean solution(int n) {
        return checkIfHappy(n, new HashSet<>());
    }

    private static boolean checkIfHappy(int n, Set<Integer> seenBefore){
        int newN = 0;
        while (n > 0) {
            int x = n % 10;
            newN += x*x;
            n /= 10;
        }
        if (newN == 1)
            return true;
        if (seenBefore.contains(newN))
            return false;
        seenBefore.add(newN);
        return checkIfHappy(newN, seenBefore);
    }

}
