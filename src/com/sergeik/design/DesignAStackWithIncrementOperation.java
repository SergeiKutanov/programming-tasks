package com.sergeik.design;

public class DesignAStackWithIncrementOperation {

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3); // Stack is Empty []
        customStack.push(1);                          // stack becomes [1]
        customStack.push(2);                          // stack becomes [1, 2]
        assert 2 == customStack.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
        customStack.push(2);                          // stack becomes [1, 2]
        customStack.push(3);                          // stack becomes [1, 2, 3]
        customStack.push(4);                          // stack still [1, 2, 3], Don't add another elements as size is 4
        customStack.increment(5, 100);                // stack becomes [101, 102, 103]
        customStack.increment(2, 100);                // stack becomes [201, 202, 103]
        assert 103 == customStack.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
        assert 202 == customStack.pop();                            // return 202 --> Return top of the stack 102, stack becomes [201]
        assert 201 == customStack.pop();                            // return 201 --> Return top of the stack 101, stack becomes []
        assert -1 == customStack.pop();                            // return -1 --> Stack is empty return -1.
    }

    static class CustomStack {

        int[] data;
        int idx = 0;

        public CustomStack(int maxSize) {
            data = new int[maxSize];
        }

        public void push(int x) {
            if (idx < data.length)
                data[idx++] = x;
        }

        public int pop() {
            if (idx > 0)
                return data[--idx];
            return -1;
        }

        public void increment(int k, int val) {
            for (int i = 0; i < idx && i < k; i++)
                data[i] += val;
        }
    }

}
