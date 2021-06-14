package com.sergeik.sortsearch;

import java.util.*;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence
 * of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation
 * sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be
 * returned as a list of the words [beginWord, s1, s2, ..., sk].
 */
public class WordLadderII {

    public static void main(String[] args) {
        List<List<String>> res, exp;

        res = solution("red", "tax", Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"));
        exp = Arrays.asList(
                Arrays.asList("red", "ted", "tad", "tax"),
                Arrays.asList("red", "ted", "tex", "tax"),
                Arrays.asList("red", "rex", "tex", "tax")
        );
        for (int i = 0; i < exp.size(); i++) {
            assert Arrays.equals(exp.get(i).toArray(), res.get(i).toArray());
        }


        res = solution("a", "c", Arrays.asList("a", "b", "c"));
        exp = Arrays.asList(
                Arrays.asList("a", "c")
        );
        for (int i = 0; i < exp.size(); i++) {
            assert Arrays.equals(exp.get(i).toArray(), res.get(i).toArray());
        }


        res = solution("hit", "cog", Arrays.asList("hot","dot","dog","lot","log"));
        assert res.size() == 0;

        res = solution("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        exp = Arrays.asList(
                Arrays.asList("hit", "hot", "dot", "dog", "cog"),
                Arrays.asList("hit", "hot", "lot", "log", "cog")
        );
        for (int i = 0; i < exp.size(); i++) {
            assert Arrays.equals(exp.get(i).toArray(), res.get(i).toArray());
        }
    }

    private static List<List<String>> solution(String beginWord, String endWord, List<String> wordList) {
        List<String> path = new LinkedList<>();
        List<List<String>> result = new LinkedList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> dict = new HashSet<>(wordList);
        buildGraph(beginWord, endWord, graph, dict);
        dfs(beginWord, endWord, graph, path, result);
        return result;
    }

    private static void buildGraph(String beginWord, String endWord, Map<String, List<String>> graph, Set<String> dict) {
        Set<String> visited = new HashSet<>();
        Set<String> toVisit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        boolean found = false;
        queue.offer(beginWord);
        toVisit.add(beginWord);

        while (!queue.isEmpty()) {
            visited.addAll(toVisit);
            toVisit.clear();
            int levelSize = queue.size();

            while (levelSize-- > 0) {
                String w = queue.poll();
                List<String> neighbours = getNeighbours(w, dict);
                for (String n: neighbours) {
                    if (n.equals(endWord))
                        found = true;
                    if (!visited.contains(n)) {
                        if (!graph.containsKey(w))
                            graph.put(w, new LinkedList<>());
                        graph.get(w).add(n);
                    }
                    if (!visited.contains(n) && !toVisit.contains(n)) {
                        queue.offer(n);
                        toVisit.add(n);
                    }
                }
            }

            if (found)
                break;
        }

    }

    private static List<String> getNeighbours(String w, Set<String> dict) {
        List<String> res = new LinkedList<>();
        char[] chars = w.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (chars[i] == ch)
                    continue;
                char tmp = chars[i];
                chars[i] = ch;
                String target = String.valueOf(chars);
                if (dict.contains(target))
                    res.add(target);
                chars[i] = tmp;
            }
        }
        return res;
    }

    private static void dfs(String curWord, String endWord, Map<String, List<String>> graph, List<String> path, List<List<String>> res) {
        path.add(curWord);
        if (curWord.equals(endWord))
            res.add(new LinkedList<>(path));
        else if (graph.containsKey(curWord)) {
            for (String n: graph.get(curWord)) {
                dfs(n, endWord, graph, path, res);
            }
        }
        path.remove(path.size() - 1);
    }

}
