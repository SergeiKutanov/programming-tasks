package com.sergeik.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string s, sort it in decreasing order based on the frequency of characters, and return the sorted string.
 */
public class SortCharactersByFrequency {

    public static void main(String[] args) {
        assert "eert".equals(solution("tree"));
    }

    private static String solution(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        PriorityQueue<Character> heap = new PriorityQueue<>((a,b) -> map.get(b) - map.get(a));
        for (Character ch: map.keySet())
            heap.offer(ch);
        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            char ch = heap.poll();
            for (int i = 0; i < map.get(ch); i++)
                sb.append(ch);
        }
        return sb.toString();
    }

}
