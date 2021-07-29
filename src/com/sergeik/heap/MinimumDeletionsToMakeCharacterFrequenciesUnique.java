package com.sergeik.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A string s is called good if there are no two different characters in s that have the same frequency.
 *
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 *
 * The frequency of a character in a string is the number of times it appears in the string. For example, in the
 * string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

    public static void main(String[] args) {
        assert 2 == solution("bbcebab");
        assert 2 == solution("aaabbbcc");
    }

    private static int solution(String s) {
        int[] count = new int[26];
        int res = 0;
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0 && !used.add(count[i])) {
                count[i]--;
                res++;
            }
        }
        return res;
    }

    private static int heapSolution(String s) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a']++;
        for (int n: count)
            if (n > 0)
                heap.offer(n);
        int res = 0;
        while (!heap.isEmpty()) {
            int fr = heap.poll();
            if (!heap.isEmpty() && fr == heap.peek()) {
                res += 1;
                fr--;
                if (fr > 0)
                    heap.offer(fr);
            }
        }
        return res;
    }

}
