package com.sergeik.bits;

import java.util.*;

/**
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
 * invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that is valid with
 * respect to the puzzle puzzles[i].
 */
public class NumberOfValidWordsInEachPuzzle {

    public static void main(String[] args) {
        assert Arrays.asList(1,1,3,2,4,0).equals(solution(
                new String[] {"aaaa","asas","able","ability","actt","actor","access"},
                new String[] {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"}
        ));
    }

    private static List<Integer> solution(String[] words, String[] puzzles) {
        int[] masks = new int[words.length];
        for (int i = 0; i < masks.length; i++) {
            int mask = 0;
            for (int j = 0; j < words[i].length(); j++)
                mask |= 1 << (words[i].charAt(j) - 'a');
            masks[i] = mask;
        }

        List<Integer> res = new ArrayList<>();
        for (String puzzle: puzzles) {
            int pMask = 0;
            int firstLetter = 1 << (puzzle.charAt(0) - 'a');
            for (int i = 0; i < puzzle.length(); i++)
                pMask |= 1 << (puzzle.charAt(i) - 'a');
            int count = 0;
            for (int i = 0; i < masks.length; i++) {
                if ((masks[i] & firstLetter) != firstLetter)
                    continue;
                if ((masks[i] & pMask) == masks[i])
                    count++;
            }
            res.add(count);
        }

        return res;
    }

}
