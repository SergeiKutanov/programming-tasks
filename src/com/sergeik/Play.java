package com.sergeik;


import com.sergeik.trees.TreeNode;

import java.util.*;

public class Play {

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
        Set<String> dict = new HashSet<>(wordList), seen = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        seen.add(beginWord);
        List<String> path = new LinkedList<>();
        Map<String, List<String>> graph = buildGraph(beginWord, endWord, dict);
        dfs(graph, beginWord, endWord, path, res);
        return res;
    }

    private static void dfs(Map<String, List<String>> graph, String word, String endWord, List<String> path, List<List<String>> res) {
        path.add(word);
        if (word.equals(endWord))
            res.add(new LinkedList<>(path));
        else if (graph.containsKey(word)) {
            for (String neighbour: graph.get(word))
                dfs(graph, neighbour, endWord, path, res);
        }
        path.remove(path.size() - 1);
    }

    private static Map<String, List<String>> buildGraph(String word, String endWord, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>(), toVisit = new HashSet<>();
        Map<String, List<String>> graph = new HashMap<>();
        queue.add(word);
        graph.put(word, new LinkedList<>());
        toVisit.add(word);
        boolean found = false;

        visited.add(word);
        while (!queue.isEmpty()) {
            visited.addAll(toVisit);
            toVisit.clear();
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                String w = queue.poll();
                for (String n: getNeighbours(w, dict)) {
                    if (n.equals(endWord)) {
                        found = true;
                    }
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

            if (found) break;;
        }

        return graph;

    }


    private static List<String> getNeighbours(String w, Set<String> dict) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < w.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == w.charAt(i))
                    continue;
                String n = w.substring(0, i) + ch + w.substring(i + 1);
                if (dict.contains(n))
                    res.add(n);
            }
        }
        return res;
    }

}
