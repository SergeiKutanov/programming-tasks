package com.sergeik.design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and
 * end with a special character '#').
 *
 * You are given a string array sentences and an integer array times both of length n where sentences[i]
 * is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed.
 * For each input character except '#', return the top 3 historical hot sentences that have the same prefix
 * as the part of the sentence already typed.
 *
 * Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several
 * sentences have the same hot degree, use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an
 * empty list.
 * Implement the AutocompleteSystem class:
 *
 * AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
 * List<String> input(char c) This indicates that the user typed the character c.
 * Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
 * Returns the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.
 * If there are fewer than 3 matches, return them all.
 */
public class DesignSearchAutocompleteSystem {

    public static void main(String[] args) {
        AutocompleteSystem obj = new AutocompleteSystem(
                new String[] {"i love you", "island", "iroman", "i love leetcode"},
                new int[]{5, 3, 2, 2});
        assert Arrays.equals(
                obj.input('i').toArray(),
                new String[] {"i love you", "island", "i love leetcode"}
        ); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
        assert Arrays.equals(
                obj.input(' ').toArray(),
                new String[] {"i love you", "i love leetcode"}
        ); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
        assert Arrays.equals(
                obj.input('a').toArray(),
                new String[0]
        ); // return []. There are no sentences that have prefix "i a".
        assert Arrays.equals(
                obj.input('#').toArray(),
                new String[0]
        ); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
    }

    static class AutocompleteSystem {

        Node root = new Node();
        StringBuilder sb;

        public AutocompleteSystem(String[] sentences, int[] times) {
            for (int i = 0; i < sentences.length; i++)
                for (int j = 0; j < times[i]; j++)
                    root.insert(sentences[i]);
            sb = new StringBuilder();
        }

        public List<String> input(char c) {
            if (c == '#') {
                root.insert(sb.toString());
                sb = new StringBuilder();
                return new LinkedList<>();
            }

            sb.append(c);
            List<Node> nodes = root.lookup(sb.toString());
            PriorityQueue<Node> queue = new PriorityQueue<>((a,b) -> {
                if (b.times != a.times)
                    return b.times - a.times;
                return a.sentence.compareTo(b.sentence);
            });
            for (Node node: nodes)
                queue.offer(node);

            List<String> res = new LinkedList<>();
            while (res.size() < 3 && !queue.isEmpty())
                res.add(queue.poll().sentence);

            return res;
        }

        class Node {
            String sentence;
            Node[] children;
            int times;

            Node() {
                children = new Node[27];
                times = 0;
            }

            void insert(String s) {
                Node node = this;
                for (int i = 0; i < s.length(); i++) {
                    int ch = s.charAt(i) == ' ' ? 26 : s.charAt(i) - 'a';
                    if (node.children[ch] == null)
                        node.children[ch] = new Node();
                    node = node.children[ch];
                }
                node.sentence = s;
                node.times++;
            }

            List<Node> lookup(String s) {
                List<Node> res = new LinkedList<>();
                Node node = this;
                int i = 0;
                while (node != null && i < s.length()) {
                    int ch = s.charAt(i) == ' ' ? 26 : s.charAt(i) - 'a';
                    node = node.children[ch];
                    i++;
                }
                traverse(node, res);

                return res;
            }

            void traverse(Node node, List<Node> res) {
                if (node == null)
                    return;
                if (node.sentence != null)
                    res.add(node);
                for (Node child: node.children)
                    node.traverse(child, res);
            }
        }
    }

}
