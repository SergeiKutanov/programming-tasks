package com.sergeik.strings;

/**
 * You are given two strings a and b that consist of lowercase letters. In one operation, you can change
 * any character in a or b to any lowercase letter.
 *
 * Your goal is to satisfy one of the following three conditions:
 *
 * Every letter in a is strictly less than every letter in b in the alphabet.
 * Every letter in b is strictly less than every letter in a in the alphabet.
 * Both a and b consist of only one distinct letter.
 * Return the minimum number of operations needed to achieve your goal.
 */
public class ChangeMinimumCharactersToSatisfyOneOfThreeConditions {

    public static void main(String[] args) {
        assert 2 == solution("ace", "abe");
        assert 0 == solution("e", "e");
        assert 2 == solution("bd", "beae");
        assert 1 == solution("ae", "b");
        assert 2 == solution("aba", "caa");
        assert 3 == solution("dabadd", "cda");
    }

    private static int solution(String a, String b) {
        int[] aFr = new int[26], bFr = new int[26];
        int res = a.length() + b.length();
        for (int i = 0; i < a.length(); i++)
            aFr[a.charAt(i) - 'a']++;
        for (int i = 0; i < b.length(); i++)
            bFr[b.charAt(i) - 'a']++;

        for (int i = 0; i < 26; i++) {
            res = Math.min(res, a.length() + b.length() - aFr[i] - bFr[i]); //third case
            if (i > 0) {
                aFr[i] += aFr[i - 1];
                bFr[i] += bFr[i - 1];
            }
            if (i < 25) {
                res = Math.min(res, a.length() - aFr[i] + bFr[i]);  //first case
                res = Math.min(res, b.length() - bFr[i] + aFr[i]);  //second case
            }
        }
        return res;
    }

}
