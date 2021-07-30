package com.sergeik.backtracking;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, we can transform every letter individually to be lowercase or uppercase to create another string.
 *
 * Return a list of all possible strings we could create. You can return the output in any order.
 */
public class LetterCasePermutation {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"c", "C"},
                solution("c").toArray()
        );
        assert Arrays.equals(
                new String[] {"3z4", "3Z4"},
                solution("3z4").toArray()
        );
        assert Arrays.equals(
                new String[] {"12345"},
                solution("12345").toArray()
        );
        assert Arrays.equals(
                new String[] {"a1b2","A1b2","A1B2","a1B2"},
                solution("a1b2").toArray()
        );
    }

    private static List<String> solution(String s) {
        List<String> res = new LinkedList<>();
        backtrack(new StringBuilder(s), 0, res);
        return res;
    }

    private static void backtrack(StringBuilder s, int idx, List<String> res) {
        res.add(s.toString());
        if (idx == s.length())
            return;
        for (int i = idx; i < s.length(); i++) {
            char ch = s.charAt(i);
            char chInit = ch;
            if (ch >= 'a' && ch <= 'z') {
                ch = (char)('A' + (ch - 'a'));
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char)('a' + (ch - 'A'));
            } else {
                continue;
            }
            s.setCharAt(i, ch);
            backtrack(s, i + 1, res);
            s.setCharAt(i, chInit);
        }
    }

}
