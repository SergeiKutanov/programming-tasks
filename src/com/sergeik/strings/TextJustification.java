package com.sergeik.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth
 * characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra
 * spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not
 * divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 */
public class TextJustification {

    public static void main(String[] args) {
        List<String> res, exp;

        res = solution(
                new String[] {"Science","is","what","we","understand","well","enough","to","explain","to","a",
                        "computer.","Art","is","everything","else","we","do"},
                20
        );
        exp = Arrays.asList("Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  ");
        for (int i = 0; i < exp.size(); i++)
            assert exp.get(i).equals(res.get(i));


        res = solution(new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16);
        exp = Arrays.asList("This    is    an", "example  of text", "justification.  ");
        for (int i = 0; i < exp.size(); i++)
            assert exp.get(i).equals(res.get(i));


        res = solution(new String[] {"What","must","be","acknowledgment","shall","be"}, 16);
        exp = Arrays.asList("What   must   be", "acknowledgment  ", "shall be        ");
        for (int i = 0; i < exp.size(); i++)
            assert exp.get(i).equals(res.get(i));
    }

    private static List<String> solution(String[] words, int maxWidth) {
        List<String> res = new LinkedList<>();
        dfs(words, 0, maxWidth, res);
        return res;
    }

    private static void dfs(String[] words, int idx, int maxWidth, List<String> res) {
        if (idx == words.length)
            return;
        List<String> line = new ArrayList<>();
        int len = 0;
        while (idx < words.length && (len + words[idx].length() + line.size() - 1) < maxWidth) {
            len += words[idx].length();
            line.add(words[idx++]);
        }
        StringBuilder sb = new StringBuilder();
        if (idx < words.length) {
            int spacesPlaces = Math.max(1, line.size() - 1);
            int spacesLeft = maxWidth - len;
            for (int i = 0; i < line.size(); i++) {
                sb.append(line.get(i));
                if (spacesLeft > 0) {
                    int spaces = spacesLeft / spacesPlaces + (spacesLeft % spacesPlaces > 0 ? 1 : 0);
                    len -= line.get(i).length();
                    spacesPlaces--;
                    spacesLeft -= spaces;

                    while (spaces-- > 0)
                        sb.append(" ");
                }
            }
        } else {
            for (String w: line) {
                sb.append(w);
                if (sb.length() < maxWidth)
                    sb.append(" ");
            }
            while (sb.length() < maxWidth)
                sb.append(" ");
        }
        res.add(sb.toString());
        dfs(words, idx, maxWidth, res);
    }

}
