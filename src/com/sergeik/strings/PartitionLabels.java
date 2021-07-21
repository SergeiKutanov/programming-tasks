package com.sergeik.strings;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given a string s. We want to partition the string into as many parts as possible so that each
 * letter appears in at most one part.
 *
 * Return a list of integers representing the size of these parts.
 */
public class PartitionLabels {

    public static void main(String[] args) {
        assert Arrays.equals(
                new Integer[] {1,1,4,2,1,1},
                solution("qvmwtmzzse").toArray()
        );
        assert Arrays.equals(
                new Integer[] {10},
                solution("eccbbbbdec").toArray()
        );
        assert Arrays.equals(
                new Integer[] {9,7,8},
                solution("ababcbacadefegdehijhklij").toArray()
        );
    }

    private static List<Integer> solution(String s) {
        int[] pos = new int[26];
        for (int i = 0; i < s.length(); i++)
            pos[s.charAt(i) - 'a'] = i;
        List<Integer> res = new LinkedList<>();
        int idx = 0;
        while (idx < s.length()) {
            int ch = s.charAt(idx) - 'a';
            int blockStart = idx;
            int maxPos = pos[ch];
            while (idx < maxPos) {
                idx++;
                maxPos = Math.max(maxPos, pos[s.charAt(idx) - 'a']);
            }
            idx++;
            res.add(idx - blockStart);
        }
        return res;
    }

}
