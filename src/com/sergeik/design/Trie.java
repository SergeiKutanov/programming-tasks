package com.sergeik.design;

public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        assert trie.search("apple");   // return True
        assert !trie.search("app");     // return False
        assert trie.startsWith("app"); // return True
        trie.insert("app");
        assert trie.search("app");     // return True
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            int ch = word.charAt(i) - 'a';
            if (node.children[ch] == null) {
                node.children[ch] = new Node();
            }
            node = node.children[ch];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            int ch = word.charAt(i) - 'a';
            if (node.children[ch] == null)
                return false;
            node = node.children[ch];
        }
        return node.isWord;
    }

    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int ch = prefix.charAt(i) - 'a';
            if (node.children[ch] == null)
                return false;
            node = node.children[ch];
        }
        return true;
    }

    class Node {
        Node[] children = new Node[26];
        boolean isWord = false;
    }

}
