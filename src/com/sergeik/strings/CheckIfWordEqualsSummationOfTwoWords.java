package com.sergeik.strings;

/**
 * The letter value of a letter is its position in the alphabet starting from 0 (i.e. 'a' -> 0, 'b' -> 1, 'c' -> 2, etc.).
 *
 * The numerical value of some string of lowercase English letters s is the concatenation of the letter values
 * of each letter in s, which is then converted into an integer.
 *
 * For example, if s = "acb", we concatenate each letter's letter value, resulting in "021". After converting it,
 * we get 21.
 * You are given three strings firstWord, secondWord, and targetWord, each consisting of lowercase English letters
 * 'a' through 'j' inclusive.
 *
 * Return true if the summation of the numerical values of firstWord and secondWord equals the numerical value of
 * targetWord, or false otherwise.
 */
public class CheckIfWordEqualsSummationOfTwoWords {

    public static void main(String[] args) {
        assert solution("j", "j", "bi");
        assert !solution("a", "a", "f");
        assert solution("acb", "cba", "cdb");
        assert !solution("aaa", "a", "aaab");
        assert solution("aaa", "a", "aaa");
    }

    private static boolean solution(String firstWord, String secondWord, String targetWord) {
        int idx = 1;
        int carryOver = 0;
        while (idx <= firstWord.length() || idx <= secondWord.length() || idx <= targetWord.length()) {
            int sum = carryOver;
            int offset = firstWord.length() - idx;
            if (offset < firstWord.length() && offset >= 0)
                sum += firstWord.charAt(firstWord.length() - idx) - 'a';
            offset = secondWord.length() - idx;
            if (offset < secondWord.length() && offset >= 0)
                sum += secondWord.charAt(secondWord.length() - idx) - 'a';
            carryOver = sum / 10;
            sum %= 10;
            offset = targetWord.length() - idx;
            if (offset < targetWord.length() && offset >= 0 && targetWord.charAt(offset) - 'a' != sum)
                return false;
            idx++;
        }
        return true;
    }

}
