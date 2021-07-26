package com.sergeik.dynamic;

/**
 * Given two strings s and t, find the number of ways you can choose a non-empty substring of s and replace a
 * single character by a different character such that the resulting substring is a substring of t. In other words,
 * find the number of substrings in s that differ from some substring in t by exactly one character.
 *
 * For example, the underlined substrings in "computer" and "computation" only differ by the 'e'/'a', so this is a
 * valid way.
 *
 * Return the number of substrings that satisfy the condition above.
 *
 * A substring is a contiguous sequence of characters within a string.
 */
public class CountSubstringsThatDifferByOneCharacter {

    public static void main(String[] args) {
        assert 10 == solution("abe", "bbc");
        assert 6 == solution("aba", "baba");
    }

    private static int solution(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                int miss = 0;
                for (int pos = 0; i + pos < s.length() && j + pos < t.length(); pos++) {
                    if (s.charAt(pos + i) != t.charAt(j + pos))
                        miss++;
                    if (miss > 1)
                        break;
                    res += miss;
                }
            }
        }
        return res;
    }

    private static int trieSolution(String s, String t) {
        Node root = new Node();
        for (int i = 0; i < t.length(); i++) {
            for (int j = i + 1; j <= t.length(); j++) {
                root.insert(t.substring(i, j));
            }
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String w = s.substring(i, j);
                for (int k = 0; k < w.length(); k++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == w.charAt(k))
                            continue;
                        res += root.lookup(w.substring(0, k) + ch + w.substring(k + 1));
                    }
                }
            }
        }
        return res;
    }

    static class Node {
        Node[] children;
        String word;
        int count;

        Node() {
            children = new Node[26];
            count = 0;
        }

        void insert(String w) {
            Node node = this;
            for (char i: w.toCharArray()) {
                int ch = i - 'a';
                if (node.children[ch] == null)
                    node.children[ch] = new Node();
                node = node.children[ch];
            }
            node.word = w;
            node.count++;
        }

        int lookup(String word) {
            Node node = this;
            for (char i: word.toCharArray()) {
                int ch = i - 'a';
                if (node.children[ch] == null)
                    return 0;
                node = node.children[ch];
            }
            return node.count;
        }
    }

    private static int bruteForceSolution(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                for (int k = 0; k < substring.length(); k++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == substring.charAt(k)) continue;
                        String sub = substring.substring(0, k) + ch + substring.substring(k + 1);
                        int idx = 0;
                        while (idx < t.length() && (idx = t.indexOf(sub, idx)) != -1) {
                            res++; idx++;
                        }
                    }
                }
            }

        }
        return res;
    }

}
