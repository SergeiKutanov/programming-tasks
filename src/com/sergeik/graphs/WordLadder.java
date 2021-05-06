package com.sergeik.graphs;

import java.util.*;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList
 * is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words
 * in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 */
public class WordLadder {

    public static void main(String[] args) {

        assert 3 == solution(
                "hot",
                "dog",
                Arrays.asList("hot", "dog", "cog", "pot", "dot")
        );

        assert 0 == solution(
                "hit",
                "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log")
        );

        assert 5 == solution(
                "hit",
                "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")
        );
    }

    private static int solution(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>();
        dictionary.addAll(wordList);

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        queue.add(null); //level separation

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {
            String word = queue.poll();
            if (null != word) {
                //generate all possible words
                for (int i = 0; i < word.length(); i++) {
                    char[] w = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        w[i] = ch;
                        String newWord = new String(w);
                        if (dictionary.contains(newWord)) {
                            if (newWord.equals(endWord))
                                return ++level;
                            if (!visited.contains(newWord)) {
                                queue.add(newWord);
                                visited.add(newWord);
                            }
                        }
                    }
                }
            } else {
                level++;
                if (!queue.isEmpty()) {
                    queue.add(null); //done with one level
                }
            }
        }

        return 0;
    }

}
