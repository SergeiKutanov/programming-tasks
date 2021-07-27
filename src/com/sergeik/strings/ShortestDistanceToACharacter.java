package com.sergeik.strings;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Given a string s and a character c that occurs in s, return an array of integers answer where
 * answer.length == s.length and answer[i] is the distance from index i to the closest occurrence of character c in s.
 *
 * The distance between two indices i and j is abs(i - j), where abs is the absolute value function.
 */
public class ShortestDistanceToACharacter {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {3,2,1,0,1,0,0,1,2,2,1,0},
                solution("loveleetcode", 'e')
        );
    }

    private static int[] solution(String s, char c) {
        int[] res = new int[s.length()];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c)
                continue;
            res[i] = 0;
            int idx = i - 1;
            while (idx >= 0 && s.charAt(idx) != c && Math.abs(i - idx) < res[idx]) {
                res[idx] = Math.abs(i - idx);
                idx--;
            }
            idx = i + 1;
            while (idx < res.length && s.charAt(idx) != c && Math.abs(i - idx) < res[idx]) {
                res[idx] = Math.abs(i - idx);
                idx++;
            }
        }
        return res;
    }

    private static int[] treeSetSolution(String s, char c) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c)
                set.add(i);
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            Integer ceil = set.ceiling(i);
            Integer floor = set.floor(i);
            int dist = Integer.MAX_VALUE;
            if (ceil != null)
                dist = Math.min(dist, Math.abs(ceil - i));
            if (floor != null)
                dist = Math.min(dist, Math.abs(floor - i));
            res[i] = dist;
        }
        return res;
    }

}
