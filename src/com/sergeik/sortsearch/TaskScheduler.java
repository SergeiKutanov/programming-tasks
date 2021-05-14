package com.sergeik.sortsearch;

import java.util.Arrays;

/**
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents
 * a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time,
 * the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks
 * (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 */
public class TaskScheduler {

    public static void main(String[] args) {
        assert 8 == solution(new char[] {'A', 'A', 'A', 'B', 'B', 'B'}, 2);
    }

    private static int solution(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char ch: tasks) {
            freq[ch - 'A']++;
        }
        Arrays.sort(freq);
        int i = 25;
        while (i >= 0 && freq[i] == freq[25]) i--;

        /**
         * (freq[25] - 1) * n - number of elements in frames between the most frequent element - AXXAXXA
         * freq[25] - number of the most frequent element (number of As)
         * 25 - i - 1 - the number that has to be added behind the main frame
         */
        return Math.max(tasks.length, (freq[25] - 1) * n + freq[25] + (25 - i - 1));
    }

}
