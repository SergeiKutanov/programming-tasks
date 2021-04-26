package com.sergeik.design;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 */
public class MinimumStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStackWithExtraData();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assert -3 == minStack.getMin(); // return -3
        minStack.pop();
        assert 0 == minStack.top();    // return 0
        assert -2 == minStack.getMin(); // return -2
    }

}

interface MinStack {
    void push(int val);

    void pop();

    int top();

    int getMin();
}

class MinWithLookupStack implements MinStack {

    private int[] data = new int[1000];
    private int currentMin = -1;
    private int tail = -1;

    /** initialize your data structure here. */
    public MinWithLookupStack() {}

    public void push(int val) {
        if (tail == data.length - 1) {
            int[] newData = new int[data.length + 1000];
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        data[++tail] = val;
        if (currentMin == -1 || val < data[currentMin]) {
            currentMin = tail;
        }

    }

    public void pop() {
        if (tail < 0)
            throw new IllegalStateException();
        tail--;
        if (tail + 1 == currentMin) {
            findMinimum();
        }
    }

    private void findMinimum() {
        if (tail < 0) {
            currentMin = -1;
            return;
        }
        currentMin = 0;
        for (int i = 1; i <= tail; i++) {
            if (data[currentMin] > data[i]) {
                currentMin = i;
            }
        }
    }

    public int top() {
        if (tail < 0)
            throw new IllegalStateException();
        return data[tail];
    }

    public int getMin() {
        if (currentMin < 0)
            throw new IllegalStateException();
        return data[currentMin];
    }

}

class MinStackWithExtraData implements MinStack {

    private class Node {
        int v;
        int min;

        Node(int v, int m) {
            this.v = v;
            this.min = m;
        }
    }

    private Node[] data = new Node[1000];
    private int tail = -1;

    /** initialize your data structure here. */
    public MinStackWithExtraData() {

    }

    public void push(int val) {
        if (tail == data.length - 1) {
            Node[] newData = new Node[data.length + 1000];
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        int min = Integer.MAX_VALUE;
        if (tail > -1) {
            min = data[tail].min;
        }
        if (val < min)
            min = val;
        data[++tail] = new Node(val, min);
    }

    public void pop() {
        if (tail < 0)
            throw new IllegalStateException();
        tail--;
    }

    public int top() {
        if (tail < 0)
            throw new IllegalStateException();
        return data[tail].v;
    }

    public int getMin() {
        if (tail < 0)
            throw new IllegalStateException();
        return data[tail].min;
    }

}
