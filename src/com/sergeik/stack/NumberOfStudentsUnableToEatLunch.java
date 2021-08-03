package com.sergeik.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1
 * respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.
 *
 * The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a
 * stack. At each step:
 *
 * If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and
 * leave the queue.
 * Otherwise, they will leave it and go to the queue's end.
 * This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
 *
 * You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the i​​​​​​th sandwich
 * in the stack (i = 0 is the top of the stack) and students[j] is the preference of the j​​​​​​th student in the initial
 * queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.
 *
 *
 */
public class NumberOfStudentsUnableToEatLunch {

    public static void main(String[] args) {
        assert 0 == solution(new int[] {1,1,0,0}, new int[] {0,1,0,1});
    }

    private static int solution(int[] students, int[] sandwiches) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = sandwiches.length - 1; i >= 0; i--)
            stack.push(sandwiches[i]);
        for (int s: students)
            queue.offer(s);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            int count = 0;
            while (queueSize-- > 0) {
                if (queue.peek() == stack.peek()) {
                    queue.poll();
                    stack.pop();
                    count++;
                } else {
                    queue.offer(queue.poll());
                }
            }
            if (count == 0)
                return queue.size();
        }
        return 0;
    }

}
