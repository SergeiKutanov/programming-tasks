package com.sergeik.arrays;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number
 * could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map
 * to any letters.
 */
public class LetterCombinationOfAPhoneNumber {

    public static void main(String[] args) {
        List<String> expected = Arrays.asList("ad","ae","af","bd","be","bf","cd","ce","cf");
        List<String> result = solution("23");
        assert expected.equals(result);
    }

    private static List<String> solution(String digits) {

        Map<Character, List<Character>> charMap = new HashMap<>();
        charMap.put('2', Arrays.asList('a', 'b', 'c'));
        charMap.put('3', Arrays.asList('d', 'e', 'f'));
        charMap.put('4', Arrays.asList('g', 'h', 'i'));
        charMap.put('5', Arrays.asList('j', 'k', 'l'));
        charMap.put('6', Arrays.asList('m', 'n', 'o'));
        charMap.put('7', Arrays.asList('p', 'q', 'r', 's'));
        charMap.put('8', Arrays.asList('t', 'u', 'v'));
        charMap.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        List<String> result = findCombinations(digits, charMap);
        return result;
    }

    private static List<String> findCombinations(String digits, Map<Character, List<Character>> charMap) {
        List<String> newResult = new LinkedList<>();
        if (digits.length() == 0) {
            return newResult;
        }
        if (digits.length() == 1) {
            for (char ch: charMap.get(digits.charAt(0))) {
                newResult.add(String.valueOf(ch));
            }
            return newResult;
        }

        List<String> result = findCombinations(digits.substring(1), charMap);
        for (Character ch: charMap.get(digits.charAt(0))) {
            for (String str: result) {
                newResult.add(ch + str);
            }
        }
        return newResult;
    }


}
