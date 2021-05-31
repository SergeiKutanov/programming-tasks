package com.sergeik.design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests
 * at most three product names from products after each character of searchWord is typed. Suggested products
 * should have common prefix with the searchWord. If there are more than three products with a common prefix
 * return the three lexicographically minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 */
public class SearchSuggestionsSystem {

    public static void main(String[] args) {

        List<List<String>> res;
        List<List<String>> expected;

        res = solution(
                new String[] {"bags", "bats", "rats"},
                "bazz"
        );
        expected = new LinkedList<>();
        expected.add(Arrays.asList("bags", "bats"));
        expected.add(Arrays.asList("bags", "bats"));
        expected.add(Arrays.asList());
        expected.add(Arrays.asList());
        for (int i = 0; i < expected.size(); i++) {
            assert Arrays.equals(expected.get(i).toArray(), res.get(i).toArray());
        }

        res = solution(
                new String[] {"bags","baggage","banner","box","cloths"},
                "bags"
        );
        expected = new LinkedList<>();
        expected.add(Arrays.asList("baggage","bags","banner"));
        expected.add(Arrays.asList("baggage","bags","banner"));
        expected.add(Arrays.asList("baggage","bags"));
        expected.add(Arrays.asList("bags"));
        for (int i = 0; i < expected.size(); i++) {
            assert Arrays.equals(expected.get(i).toArray(), res.get(i).toArray());
        }

        res = solution(
                new String[] {"mobile","mouse","moneypot","monitor","mousepad"},
                "mouse"
        );
        expected = new LinkedList<>();
        expected.add(Arrays.asList("mobile","moneypot","monitor"));
        expected.add(Arrays.asList("mobile","moneypot","monitor"));
        expected.add(Arrays.asList("mouse", "mousepad"));
        expected.add(Arrays.asList("mouse", "mousepad"));
        expected.add(Arrays.asList("mouse", "mousepad"));
        for (int i = 0; i < expected.size(); i++) {
            assert Arrays.equals(expected.get(i).toArray(), res.get(i).toArray());
        }

        res = solution(new String[]{"havana"}, "tatiana");
        expected = new LinkedList<>();
        expected.add(Arrays.asList());
        expected.add(Arrays.asList());
        expected.add(Arrays.asList());
        expected.add(Arrays.asList());
        expected.add(Arrays.asList());
        expected.add(Arrays.asList());
        expected.add(Arrays.asList());
        for (int i = 0; i < expected.size(); i++) {
            assert Arrays.equals(expected.get(i).toArray(), res.get(i).toArray());
        }

        res = solution(new String[]{"havana"}, "havana");
        expected = new LinkedList<>();
        expected.add(Arrays.asList("havana"));
        expected.add(Arrays.asList("havana"));
        expected.add(Arrays.asList("havana"));
        expected.add(Arrays.asList("havana"));
        expected.add(Arrays.asList("havana"));
        expected.add(Arrays.asList("havana"));
        for (int i = 0; i < expected.size(); i++) {
            assert Arrays.equals(expected.get(i).toArray(), res.get(i).toArray());
        }

    }

    private static List<List<String>> solution(String[] products, String searchWord) {
        List<List<String>> res = new LinkedList<>();
        TNode root = new TNode();
        for (String w: products)
            root.insert(w);
        for (int i = 1; i <= searchWord.length(); i++) {
            List<String> lookUp = root.lookup(searchWord.substring(0, i));
            res.add(lookUp);
        }
        return res;
    }

    static class TNode {
        String word;
        TNode[] children = new TNode[26];

        void insert(String word) {
            TNode node = this;
            for (int i = 0; i < word.length(); i++) {
                int ch = word.charAt(i) - 'a';
                if (node.children[ch] == null) {
                    node.children[ch] = new TNode();
                }
                node = node.children[ch];
            }
            node.word = word;
        }

        List<String> lookup(String word) {
            TNode node = this;
            for (int i = 0; i < word.length(); i++) {
                if (node == null)
                    break;
                int ch = word.charAt(i) - 'a';
                node = node.children[ch];
            }
            List<String> res = new LinkedList<>();
            if (node != null && !node.equals(this))
                lookup(node, res);
            return res;
        }

        private void lookup(TNode root, List<String> list) {
            if (list.size() == 3)
                return;
            if (root.word != null)
                list.add(root.word);
            for (int i = 0; i < root.children.length; i++) {
                if (root.children[i] != null)
                    lookup(root.children[i], list);
            }
        }
    }

    private static List<List<String>> solutionBinarySearch(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new LinkedList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            res.add(findSuggestion(searchWord.substring(0, i), products));
        }
        return res;
    }

    private static List<String> findSuggestion(String needle, String[] haystack) {
        int l = 0;
        int r = haystack.length - 1;
        List<String> res = new LinkedList<>();
        while (l <= r && l >= 0 && r < haystack.length) {
            int m = (l + r) / 2;
            int com = needle.compareTo(haystack[m]);
            if (com == 0) {
                r = m - 1;
                break;
            } else if (haystack[m].startsWith(needle)) {
                r = m - 1;
            } else if (com > 0) {
                l = m + 1;
            } else if (com < 0){
                r = m - 1;
            }
        }
        r = Math.max(0, r);
        if (!haystack[r].startsWith(needle))
            r++;
        while (r >= 0 && r < haystack.length && haystack[r].startsWith(needle) && res.size() < 3)
            res.add(haystack[r++]);
        return res;
    }

}
