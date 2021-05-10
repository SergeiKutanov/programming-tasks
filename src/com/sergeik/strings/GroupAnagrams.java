package com.sergeik.strings;

import java.util.*;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        List<List<String>> res = solution(new String[]{"", "", ""});
        List<List<String>> expected = new LinkedList<>();
        expected.add(Arrays.asList("", "", ""));
        assert verify(expected, res);

        res = solution(new String[]{"", ""});
        expected = new LinkedList<>();
        expected.add(Arrays.asList("", ""));
        assert verify(expected, res);


        res = solution(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        expected = new LinkedList<>();
        expected.add(Arrays.asList("bat"));
        expected.add(Arrays.asList("tan", "nat"));
        expected.add(Arrays.asList("eat", "tea", "ate"));
        assert verify(expected, res);

        res = solution(new String[]{""});
        expected = new LinkedList<>();
        expected.add(Arrays.asList(""));
        assert verify(expected, res);

    }

    private static List<List<String>> solution(String[] strs) {
        if (strs.length == 0)
            return new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            //char[] chars = str.toCharArray();
            //Arrays.sort(chars);
            char[] charFreq = new char[26];
            for (char ch: str.toCharArray())
                charFreq[ch - 'a']++;
            String key = String.valueOf(charFreq);
            if (!map.containsKey(key))
                map.put(key, new LinkedList<>());
            map.get(key).add(str);
        }
        return new LinkedList<>(map.values());
    }

    private static boolean verify(List<List<String>> l1, List<List<String>> l2) {
        if (l1.size() != l2.size())
            return false;
        for (List<String> l: l1) {
            boolean found = false;
            for (List<String> ll: l2) {
                if (l.size() == ll.size()) {
                    found = Arrays.equals(l.toArray(), ll.toArray());
                }
            }
            if (!found)
                return false;
        }
        return true;
    }

}
