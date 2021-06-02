package com.sergeik.strings;

/**
 * The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
 *
 * For example, the beauty of "abaacc" is 3 - 1 = 2.
 * Given a string s, return the sum of beauty of all of its substrings.
 */
public class SumOfBeautyOfAllSubstrings {

    public static void main(String[] args) {
        assert 5 == solution("aabcb");
        assert 17 == solution("aabcbaa");
    }

    private static int solution(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] fr = new int[26];
            for (int j = i; j < s.length(); j++) {
                fr[s.charAt(j) - 'a']++;
                sum += getMax(fr) - getMin(fr);
            }
        }
        return sum;
    }

    private static int getMax(int[] arr) {
        int max = 0;
        for (int n: arr)
            max = Math.max(max, n);
        return max;
    }

    private static int getMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int n: arr) {
            if (n > 0)
                min = Math.min(min, n);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
