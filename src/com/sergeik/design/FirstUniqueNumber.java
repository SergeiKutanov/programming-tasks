package com.sergeik.design;

import java.util.HashMap;
import java.util.Map;

/**
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 *
 * Implement the FirstUnique class:
 *
 * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 * int showFirstUnique() returns the value of the first unique integer of the queue,
 * and returns -1 if there is no such integer.
 * void add(int value) insert value to the queue.
 */
public class FirstUniqueNumber {

    public static void main(String[] args) {
        FirstUnique firstUnique;

        firstUnique = new FirstUnique(new int[]{1});
        firstUnique.add(1);
        firstUnique.add(1);
        assert -1 == firstUnique.showFirstUnique();

        firstUnique = new FirstUnique(new int[] {809});
        assert 809 == firstUnique.showFirstUnique(); // return 809
        firstUnique.add(809);          // the queue is now [809,809]
        assert -1 == firstUnique.showFirstUnique(); // return -1

        firstUnique = new FirstUnique(new int[] {7,7,7,7,7,7});
        assert -1 == firstUnique.showFirstUnique(); // return -1
        firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
        firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
        firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
        firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
        firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
        assert 17 == firstUnique.showFirstUnique(); // return 17

        firstUnique = new FirstUnique(new int[] {2,3,5});
        assert 2 == firstUnique.showFirstUnique(); // return 2
        firstUnique.add(5);            // the queue is now [2,3,5,5]
        assert 2 == firstUnique.showFirstUnique(); // return 2
        firstUnique.add(2);            // the queue is now [2,3,5,5,2]
        assert 3 == firstUnique.showFirstUnique(); // return 3
        firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
        assert -1 == firstUnique.showFirstUnique(); // return -1
    }

    static class FirstUnique {

        Map<Integer, Node> nodeMap;
        Node head, tail;

        public FirstUnique(int[] nums) {
            nodeMap = new HashMap<>();
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;

            for (int n: nums)
                add(n);
        }

        public int showFirstUnique() {
            if (head.next.equals(tail))
                return -1;
            return head.next.val;
        }

        public void add(int value) {
            Node node = nodeMap.get(value);
            if (node != null && node.val != 0) {
                //remove node from map and dl list
                node.val = 0;
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else if (node == null) {
                //add node to map and end of dl list
                Node newNode = new Node(value);
                nodeMap.put(value, newNode);
                newNode.next = tail;
                newNode.prev = tail.prev;
                tail.prev.next = newNode;
                tail.prev = newNode;
            }
        }

        class Node {
            Node prev;
            Node next;
            int val;

            Node(int v) {
                val = v;
            }

        }

    }

}
