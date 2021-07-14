package com.sergeik.design;

/**
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Your implementation should support following operations:
 *
 * MyCircularDeque(k): Constructor, set the size of the deque to be k.
 * insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
 * insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
 * deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
 * deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
 * getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
 * getRear(): Gets the last item from Deque. If the deque is empty, return -1.
 * isEmpty(): Checks whether Deque is empty or not.
 * isFull(): Checks whether Deque is full or not.
 */
public class CircularDequeue {

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3); // set the size to be 3
        assert circularDeque.insertLast(1);			// return true
        assert circularDeque.insertLast(2);			// return true
        assert circularDeque.insertFront(3);			// return true
        assert !circularDeque.insertFront(4);			// return false, the queue is full
        assert 2 == circularDeque.getRear();  			// return 2
        assert circularDeque.isFull();				// return true
        assert circularDeque.deleteLast();			// return true
        assert circularDeque.insertFront(4);			// return true
        assert 4 == circularDeque.getFront();			// return 4
    }

    static class MyCircularDeque {

        int cap;
        int size;
        Node head, tail;

        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            cap = k;
            size = k;
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if (cap == 0)
                return false;
            Node node = new Node(value);
            head.insertAfter(node);
            cap--;
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if (cap == 0)
                return false;
            tail.prev.insertAfter(new Node(value));
            cap--;
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if (cap == size)
                return false;
            head.next.delete();
            cap++;
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if (cap == size)
                return false;
            tail.prev.delete();
            cap++;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            return head.next.val;
        }

        /** Get the last item from the deque. */
        public int getRear() {
            return tail.prev.val;
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return cap == size;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return cap == 0;
        }

        private class Node {
            Node prev;
            Node next;
            int val;

            Node(int v) {
                val = v;
            }

            void insertAfter(Node n) {
                Node tmp = this.next;
                this.next = n;
                n.next = tmp;
                n.prev = this;
                tmp.prev = n;
            }

            void delete() {
                this.prev.next = this.next;
                this.next.prev = this.prev;
            }

        }
    }


}
