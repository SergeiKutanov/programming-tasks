package com.sergeik.hashtable;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
 * then the word with the lower alphabetical order comes first.
 */
public class TopKFrequentWords {

    public static void main(String[] args) {

        assert Arrays.equals(
                new String[] {"i", "love"},
                solution(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2).toArray()
        );

        assert Arrays.equals(
                new String[] {"the", "is", "sunny", "day"},
                solution(new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4)
                    .toArray()
        );
    }

    private static List<String> solution(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w: words)
            map.put(w, map.getOrDefault(w, 0) + 1);
        Queue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((a,b) -> {
            if (b.getValue() == a.getValue()) {
                return a.getKey().compareTo(b.getKey());
            }
            return b.getValue() - a.getValue();
        });
        for (Map.Entry<String, Integer> e: map.entrySet())
            heap.offer(e);

        List<String> res = new LinkedList<>();
        while (!heap.isEmpty() && res.size() < k) {
            res.add(heap.poll().getKey());
        }
        return res;
    }

    private static List<String> monoStackSolution(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w: words)
            map.put(w, map.getOrDefault(w, 0) + 1);

        Stack<Map.Entry<String, Integer>> monoStack = new Stack<>();
        Stack<Map.Entry<String, Integer>> buffer = new Stack<>();
        for (Map.Entry<String, Integer> e: map.entrySet()) {
            while (!monoStack.isEmpty()
                    && (monoStack.peek().getValue() < e.getValue()
                        || monoStack.peek().getValue() == e.getValue()
                            && monoStack.peek().getKey().compareTo(e.getKey()) > 0
            )) {
                buffer.push(monoStack.pop());
            }
            if (monoStack.size() < k) {
                monoStack.push(e);
            }
            while (monoStack.size() < k && !buffer.isEmpty())
                monoStack.push(buffer.pop());
        }
        List<String> res = new LinkedList<>();
        while (monoStack.size() > 0)
            res.add(0, monoStack.pop().getKey());
        return res;
    }

}
