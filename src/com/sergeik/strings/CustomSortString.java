package com.sergeik.strings;

/**
 * order and str are strings composed of lowercase letters. In order, no letter occurs more than once.
 *
 * order was sorted in some custom order previously. We want to permute the characters of str so that they match
 * the order that order was sorted. More specifically, if x occurs before y in order, then x should occur
 * before y in the returned string.
 *
 * Return any permutation of str (as a string) that satisfies this property.
 */
public class CustomSortString {

    public static void main(String[] args) {
        assert "ccbbaad".equals(solution("cba", "abcdbca"));
    }

    private static String solution(String order, String str) {
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i) - 'a']++;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            int ch = order.charAt(i) - 'a';
            while (count[ch]-- > 0)
                res.append(order.charAt(i));
        }
        for (int i = 0; i < count.length; i++)
            while (count[i]-- > 0)
                res.append((char) (i + 'a'));
        return res.toString();
    }

}
