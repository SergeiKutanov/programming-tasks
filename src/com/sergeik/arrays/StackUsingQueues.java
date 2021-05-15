package com.sergeik.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a last in first out (LIFO) stack using only two queues. The implemented stack should support 
 * all the functions of a normal queue (push, top, pop, and empty).
 */
public class StackUsingQueues {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        assert 3 == myStack.top();
        assert 3 == myStack.pop();
        assert 2 == myStack.top();
        assert 2 == myStack.pop();
        assert 1 == myStack.top;
        assert !myStack.empty();
        assert 1 == myStack.pop();
        assert myStack.empty();
    }

    static class MyStack {

        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();
        private int size = 0;
        private int top = -1;

        /** Initialize your data structure here. */
        public MyStack() {

        }

        /** Push element x onto stack. */
        public void push(int x) {
            size++;
            top = x;
            q1.offer(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (size > 0) {
                for (int i = size; i > 1; i--) {
                    int n = q1.poll();
                    q2.offer(n);
                    top = n;
                }
                size--;
                Queue<Integer> tmp = q1;
                q1 = q2;
                q2 = tmp;
                return q2.poll();
            }
            return -1;
        }

        /** Get the top element. */
        public int top() {
            if (size > 0)
                return top;
            return -1;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q1.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

}
