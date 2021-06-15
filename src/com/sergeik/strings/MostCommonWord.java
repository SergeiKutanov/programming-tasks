package com.sergeik.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string paragraph and a string array of the banned words banned, return the most frequent word that
 * is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.
 *
 * The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 */
public class MostCommonWord {

    public static void main(String[] args) {
        assert "bob".equals(solution(
                "Bob",
                new String[] {}
        ));
        assert "a".equals(solution(
                "a.",
                new String[] {}
        ));
        assert "ball".equals(solution(
                "Bob hit a ball, the hit BALL flew far after it was hit.",
                new String[] {"hit"}
        ));
    }

    private static String solution(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>();
        Map<String, Integer> fr = new HashMap<>();
        for (String b: banned)
            ban.add(b.toLowerCase());

        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < paragraph.length()) {
            while (idx < paragraph.length() && ((paragraph.charAt(idx) >= 'a' && paragraph.charAt(idx) <= 'z') || (paragraph.charAt(idx) >= 'A' && paragraph.charAt(idx) <= 'Z'))) {
                sb.append(paragraph.charAt(idx++));
            }
            idx++;
            if (sb.length() > 0) {
                String word = sb.toString().toLowerCase();
                if (!ban.contains(word)) {
                    fr.put(word, fr.getOrDefault(word, 0) + 1);
                }
                sb = new StringBuilder();
            }
        }

        String res = "";
        int count = 0;
        for (String w: fr.keySet()) {
            if (fr.get(w) > count) {
                res = w;
                count = fr.get(w);
            }
        }
        return res;
    }

}
