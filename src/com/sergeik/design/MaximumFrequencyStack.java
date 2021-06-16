package com.sergeik.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 *
 * Implement the FreqStack class:
 *
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 */
public class MaximumFrequencyStack {

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]
        assert 5 == freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        assert 7 == freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        assert 5 == freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        assert 4 == freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
    }

    static class FreqStack {

        Map<Integer, Integer> freq;
        Map<Integer, Stack<Integer>> group;
        int maxFreq;

        public FreqStack() {
            freq = new HashMap<>();
            group = new HashMap<>();
            maxFreq = 0;
        }

        public void push(int val) {
            int fr = freq.getOrDefault(val, 0) + 1;
            freq.put(val, fr);
            if (fr > maxFreq)
                maxFreq = fr;
            group.computeIfAbsent(fr, l -> new Stack<>());
            group.get(fr).push(val);
        }

        public int pop() {
            Stack<Integer> groupStack = group.get(maxFreq);
            int res = groupStack.pop();
            if (groupStack.size() == 0) {
                maxFreq = freq.get(res) - 1;
            }
            freq.put(res, freq.get(res) - 1);
            return res;
        }
    }

}
