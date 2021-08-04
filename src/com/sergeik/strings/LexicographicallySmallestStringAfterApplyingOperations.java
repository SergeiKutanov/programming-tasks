package com.sergeik.strings;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given a string s of even length consisting of digits from 0 to 9, and two integers a and b.
 *
 * You can apply either of the following two operations any number of times and in any order on s:
 *
 * Add a to all odd indices of s (0-indexed). Digits post 9 are cycled back to 0. For example, if s = "3456" and
 * a = 5, s becomes "3951".
 * Rotate s to the right by b positions. For example, if s = "3456" and b = 1, s becomes "6345".
 * Return the lexicographically smallest string you can obtain by applying the above operations any number of
 * times on s.
 *
 * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a
 * and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
 * For example, "0158" is lexicographically smaller than "0190" because the first position they differ is at the
 * third letter, and '5' comes before '9'.
 */
public class LexicographicallySmallestStringAfterApplyingOperations {

    public static void main(String[] args) {
        assert "00553311".equals(solution("43987654", 7, 3));
        assert "0011".equals(solution("0011", 4, 2));
        assert "24".equals(solution("74", 5, 1));
        assert "2050".equals(solution("5525", 9, 2));
    }

    private static String solution(String s, int a, int b) {
        Set<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String res = s;
        queue.offer(s);
        while (!queue.isEmpty()) {
            String s1 = queue.poll();
            if (res.compareTo(s1) > 0)
                res = s1;
            String added = add(s1, a);
            if (!seen.contains(added)) {
                queue.offer(added);
                seen.add(added);
            }
            String rotated = rotate(s1, b);
            if (!seen.contains(rotated)) {
                queue.offer(rotated);
                seen.add(rotated);
            }
        }
        return res;
    }

    private static String add(String s, int a) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 1; i < sb.length(); i += 2) {
            char ch = (char)(sb.charAt(i) + a);
            if (ch > '9')
                ch -= 10;
            sb.setCharAt(i, ch);
        }
        return sb.toString();
    }

    private static String rotate(String s, int b) {
        String res = s.substring(b) + s.substring(0, b);
        return res;
    }

}
