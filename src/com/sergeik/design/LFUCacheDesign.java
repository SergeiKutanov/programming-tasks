package com.sergeik.design;

import java.util.*;

/**
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
 * When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting
 * a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency),
 * the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with
 * the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use
 * counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LFUCacheDesign {

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        assert 1 == lfu.get(1);      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        assert -1 == lfu.get(2);      // return -1 (not found)
        assert 3 == lfu.get(3);      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        assert -1 == lfu.get(1);      // return -1 (not found)
        assert 3 == lfu.get(3);      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        assert 4 == lfu.get(4);      // return 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }

    static class LFUCache {

        int cap, size, min;
        Map<Integer, Node> nodeMap;
        Map<Integer, DDList> countMap;

        public LFUCache(int capacity) {
            cap = capacity;
            nodeMap = new HashMap<>();
            countMap = new HashMap<>();
        }

        public int get(int key) {
            Node node = nodeMap.get(key);
            if (node == null)
                return -1;
            update(node);
            return node.v;
        }

        public void put(int key, int value) {
            if (cap == 0)
                return;
            Node node;
            if (nodeMap.containsKey(key)) {
                node = nodeMap.get(key);
                node.v = value;
                update(node);
            } else {
                node = new Node(key, value);
                nodeMap.put(key, node);
                if (size == cap) {
                    DDList list = countMap.get(min);
                    nodeMap.remove(list.removeLast().k);
                    size--;
                }
                size++;
                min = 1;
                DDList list = countMap.getOrDefault(min, new DDList());
                list.add(node);
                countMap.put(node.c, list);
            }
        }

        private void update(Node node) {
            DDList list = countMap.get(node.c);
            list.remove(node);
            if (node.c == min && list.size == 0)
                min++;
            node.c++;
            DDList newList = countMap.getOrDefault(node.c, new DDList());
            newList.add(node);
            countMap.put(node.c, newList);
        }

        class Node {
            int v, k, c;
            Node prev;
            Node next;

            Node(int v, int k) {
                this.v = v;
                this.k = k;
                this.c = 1;
            }
        }

        class DDList {
            Node head, tail;
            int size;

            DDList() {
                head = new Node(0,0);
                tail = new Node(0,0);
                head.next = tail;
                tail.prev = head;
            }

            void add(Node node) {
                head.next.prev = node;
                node.next = head.next;
                node.prev = head;
                head.next = node;
                size++;
            }

            void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            Node removeLast() {
                if (size > 0) {
                    Node node = tail.prev;
                    remove(node);
                    return node;
                }
                return null;
            }
        }

    }

}
