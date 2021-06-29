package com.sergeik.strings;

import java.util.*;

/**
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule. You may return the answer in any order.
 */
public class RepeatedDnaSequences {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"AAAAAAAAAA"},
                solution("AAAAAAAAAAAAA").toArray()
        );
        assert Arrays.equals(
                new String[] {"AAAAACCCCC", "CCCCCAAAAA"},
                solution("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT").toArray()
        );
    }

    private static List<String> solution(String s) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new LinkedList<>();

        int left = 0, right = 10;
        while (right <= s.length()) {
            String sequence = s.substring(left, right);
            map.put(sequence, map.getOrDefault(sequence, 0) + 1);
            if (map.get(sequence) == 2)
                res.add(sequence);
            left++; right++;
        }

        return res;
    }

}
