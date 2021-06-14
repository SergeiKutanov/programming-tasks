package com.sergeik.design;

import java.util.*;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to
 * the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCacheDesign {

    public static void main(String[] args) {

        LRUCache lRUCache;

        lRUCache = new LRUCache(2);
        lRUCache.put(2,1);
        lRUCache.put(1,1);
        lRUCache.put(2,3);
        lRUCache.put(4,1);
        assert -1 == lRUCache.get(1);
        assert 3 == lRUCache.get(2);

        lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        assert 1 == lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        assert -1 == lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        assert -1 == lRUCache.get(1);    // return -1 (not found)
        assert 3 == lRUCache.get(3);    // return 3
        assert 4 == lRUCache.get(4);    // return 4

    }

    static class LRUCache {

        Map<Integer, Node> map;
        int capacity;
        Node head = new Node(0, 0);
        Node tail = new Node(0, 0);

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key))
                return -1;
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                remove(map.get(key));
            }

            if (map.size() == capacity) {
                remove(tail.prev);
            }
            Node node = new Node(key, value);
            insert(node);
        }

        private void remove(Node node) {
            map.remove(node.key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insert(Node node) {
            //cent -> head
            //cent -> node -> head
            map.put(node.key, node);
            Node headNext = head.next;
            head.next = node;
            node.prev = head;
            node.next = headNext;
            headNext.prev = node;
        }

        private class Node {
            Node prev;
            Node next;
            int val;
            int key;

            Node(int k, int v) {
                key = k;
                val = v;
            }
        }

    }

}
