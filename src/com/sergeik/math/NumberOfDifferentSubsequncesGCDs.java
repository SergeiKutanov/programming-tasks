package com.sergeik.math;


import java.util.HashSet;
import java.util.Set;

/**
 * You are given an array nums that consists of positive integers.
 *
 * The GCD of a sequence of numbers is defined as the greatest integer that divides all the numbers
 * in the sequence evenly.
 *
 * For example, the GCD of the sequence [4,6,16] is 2.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 *
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * Return the number of different GCDs among all non-empty subsequences of nums.
 */
public class NumberOfDifferentSubsequncesGCDs {

    public static void main(String[] args) {
        assert 8 == solution(new int[]{14,20,1,15,9});
        assert 5 == solution(new int[] {6,10,3});
        assert 7 == solution(new int[] {5,15,40,5,6});
    }

    private static int solution(int[] nums) {
       int max = 0;
       Set<Integer> exist = new HashSet<>();
       for (int n: nums) {
           max = Math.max(max, n);
           exist.add(n);
       }
       int count = 0;
       for (int i = 1; i <= max; i++) {
           if (findGCD(i, exist, max))
               count++;
       }
       return count;
    }

    private static boolean findGCD(int num, Set<Integer> exists, int max) {
        int val = 0;
        for (int i = num; i <= max; i += num) {
            if (exists.contains(i))
                val = gcd(i, val);
        }
        return val == num;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = a;
            a = b;
            b = t % b;
        }
        return a;
    }


}
