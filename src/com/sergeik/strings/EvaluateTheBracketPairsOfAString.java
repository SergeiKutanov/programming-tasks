package com.sergeik.strings;

import java.util.*;

/**
 * You are given a string s that contains some bracket pairs, with each pair containing a non-empty key.
 *
 * For example, in the string "(name)is(age)yearsold", there are two bracket pairs that contain
 * the keys "name" and "age".
 * You know the values of a wide range of keys. This is represented by a 2D string array knowledge
 * where each knowledge[i] = [keyi, valuei] indicates that key keyi has a value of valuei.
 *
 * You are tasked to evaluate all of the bracket pairs. When you evaluate a bracket pair that contains
 * some key keyi, you will:
 *
 * Replace keyi and the bracket pair with the key's corresponding valuei.
 * If you do not know the value of the key, you will replace keyi and the bracket pair with a question
 * mark "?" (without the quotation marks).
 * Each key will appear at most once in your knowledge. There will not be any nested brackets in s.
 *
 * Return the resulting string after evaluating all of the bracket pairs.
 */
public class EvaluateTheBracketPairsOfAString {

    public static void main(String[] args) {
        assert "ba".equals(evaluate("(a)(b)", Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "a")
        )));
        assert "yesyesyesaaa".equals(evaluate("(a)(a)(a)aaa", Arrays.asList(
                Arrays.asList("a", "yes")
        )));
        assert "hi?".equals(evaluate("hi(name)", Arrays.asList(
                Arrays.asList("a", "b")
        )));
        assert "bobistwoyearsold".equals(evaluate("(name)is(age)yearsold", Arrays.asList(
                Arrays.asList("name", "bob"),
                Arrays.asList("age", "two")
        )));
    }

    private static String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> record: knowledge) {
            map.put(record.get(0), record.get(1));
        }

        StringBuilder result = new StringBuilder();
        int idx = 0;
        StringBuilder key = new StringBuilder();
        boolean buildingKey = false;
        while (idx < s.length()) {
            char ch = s.charAt(idx);
            if (ch == '(') {
                buildingKey = true;
                key = new StringBuilder();
            } else if (ch == ')') {
                buildingKey = false;
                result.append(map.getOrDefault(key.toString(), "?"));
            } else if (buildingKey) {
                key.append(ch);
            } else result.append(ch);
            idx++;
        }
        return result.toString();
    }

}
