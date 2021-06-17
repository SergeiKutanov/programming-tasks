package com.sergeik.sortsearch;

import java.util.*;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
 * or vertically neighboring. The same letter cell may not be used more than once in a word.
 */
public class WordSearchII {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"oa","oaa"},
                solution(new char[][] {
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}
                }, new String[] {"oa","oaa"}).toArray()
        );
        assert Arrays.equals(
                new String[] {"oath", "eat"},
                solution(new char[][] {
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}
                }, new String[] {"oath","pea","eat","rain"}).toArray()
        );
    }

    private static List<String> solution(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        Node trie = new Node();
        for (String w: words)
            trie.insert(w);
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++) {
                boolean[] seen = new boolean[board.length * board[0].length];
                seen[i * board[0].length + j] = true;
                dfs(board, i, j, trie, res, seen);
            }

        List<String> ans = new LinkedList<>();
        for (String str: res)
            ans.add(str);

        return ans;
    }

    private static void dfs(char[][] board, int i, int j, Node node, Set<String> res, boolean[] seen) {
        if (i == board.length || j == board[0].length)
            return;
        int ch = board[i][j] - 'a';
        if (node.children[ch] == null)
            return;
        if (node.children[ch].isWord)
            res.add(node.children[ch].word);
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                int coor = x * board[0].length + y;
                if (!seen[coor]) {
                    seen[coor] = true;
                    dfs(board, x, y, node.children[ch], res, seen);
                    seen[coor] = false;
                }
            }
        }
    }

    static class Node {
        Node[] children = new Node[26];
        boolean isWord;
        String word;

        void insert(String w) {
            Node node = this;
            for (int i = 0; i < w.length(); i++) {
                int ch = w.charAt(i) - 'a';
                if (node.children[ch] == null)
                    node.children[ch] = new Node();
                node = node.children[ch];
            }
            node.isWord = true;
            node.word = w;
        }

    }

}
