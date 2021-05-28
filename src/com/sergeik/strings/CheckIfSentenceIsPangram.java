package com.sergeik.strings;

/**
 * A pangram is a sentence where every letter of the English alphabet appears at least once.
 *
 * Given a string sentence containing only lowercase English letters, return true if sentence
 * is a pangram, or false otherwise.
 */
public class CheckIfSentenceIsPangram {

    public static void main(String[] args) {
        assert solution("thequickbrownfoxjumpsoverthelazydog");
        assert !solution("leetcode");
    }

    private static boolean indexOfSolution(String sentence) {
        if (sentence.length() < 26) {
            return false;
        }
        String alphas = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alphas.length(); i++) {
            if (sentence.indexOf(alphas.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    private static boolean solution(String sentence) {
        int count = 0;
        for (int i = 0; i < sentence.length(); i++) {
            count |= 1 << (sentence.charAt(i) - 'a');
        }
        //int exp = 67108863; //26 ones in binary
        // (1 << 26) - 1

        return count == 67108863;
    }

}
