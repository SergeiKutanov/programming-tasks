package com.sergeik.trees;

/**
 * Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.
 *
 * Implement the WordFilter class:
 *
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix 
 * and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such
 * word in the dictionary, return -1.
 */
public class PrefixAndSuffixSearch {
    
    public static void main(String[] args) {
        WordFilter wordFilter = new WordFilter(new String[]{"apple"});
        assert 0 == wordFilter.f("a", "e");
    }

    static class WordFilter {

        private TrieNode root;

        public WordFilter(String[] words) {
            root = new TrieNode();
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words[i].length(); j++)
                    root.insert(words[i].substring(j) + '{' + words[i], i);
            }
        }

        public int f(String prefix, String suffix) {
            return root.startsWith(suffix + "{" + prefix);
        }

        public class TrieNode {
            int index;
            TrieNode[] children;

            public TrieNode() {
                children = new TrieNode[27];
            }

            public void insert(String s, int index) {
                TrieNode cur = root;
                for (int i = 0; i < s.length(); i++) {
                    int n = s.charAt(i) - 'a';
                    if (cur.children[n] == null)
                        cur.children[n] = new TrieNode();
                    cur = cur.children[n];
                    cur.index = index;
                }
            }

            public int startsWith(String s) {
                TrieNode cur = root;
                for (int i = 0; i < s.length(); i++) {
                    int n = s.charAt(i) - 'a';
                    if (cur.children[n] == null)
                        return -1;
                    cur = cur.children[n];
                }
                return cur.index;
            }

        }
    }


}
