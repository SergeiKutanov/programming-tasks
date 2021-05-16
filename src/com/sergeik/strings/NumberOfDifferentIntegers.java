package com.sergeik.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string word that consists of digits and lowercase English letters.
 *
 * You will replace every non-digit character with a space. For example, "a123bc34d8ef34" will become " 123  34 8  34".
 * Notice that you are left with some integers that are separated by at least one space: "123", "34", "8", and "34".
 *
 * Return the number of different integers after performing the replacement operations on word.
 *
 * Two integers are considered different if their decimal representations without any leading zeros are different.
 */
public class NumberOfDifferentIntegers {

    public static void main(String[] args) {
        assert 1 == solution("0a0");
        assert 1 == solution("a1b01c001");
        assert 1 == solution("167278959591294");
        assert 3 == solution("a123bc34d8ef34");
        assert 4 == solution("a123bc34d8ef35");
    }

    private static int solution(String word) {
        Set<String> set = new HashSet<>();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (char ch: word.toCharArray()) {
            if (Character.isDigit(ch)) {
                if (sb.length() == 1 && sb.charAt(0) == '0')
                    sb.replace(0, 1, String.valueOf(ch));
                else
                    sb.append(ch);
            } else {
                if (sb.length() > 0) {
                    String str = sb.toString();
                    if (!set.contains(str)) {
                        count++;
                        set.add(str);
                    }
                    sb = new StringBuilder();
                }
            }
        }
        if (sb.length() > 0) {
            if (!set.contains(sb.toString())) {
                count++;
            }
        }
        return count;
    }

}
