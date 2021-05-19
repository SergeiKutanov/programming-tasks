package com.sergeik.hashtable;

public class MyHashMap {

    public static void main(String[] args) {
        MHashMap mHashMap = new MHashMap();

        mHashMap.put(1,1);
        mHashMap.put(2,2);
        assert 1 == mHashMap.get(1);

    }

    static class MHashMap {
        private Node[] nodes = new Node[10000];

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hashKey = getKey(key);
            if (nodes[hashKey] == null) {
                nodes[hashKey] = new Node(-1, -1);
            }
            Node prev = find(nodes[hashKey], key);
            if (prev.next == null) {
                prev.next = new Node(key, value);
            } else {
                prev.next.val = value;
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int hashKey = getKey(key);
            if (nodes[hashKey] == null) {
                return -1;
            }
            Node node = find(nodes[hashKey], key);
            return node.next == null ? -1 : node.next.val;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int hashKey = getKey(key);
            if (nodes[hashKey] == null)
                return;
            Node prev = find(nodes[hashKey], key);
            if (prev.next == null)
                return;
            prev.next = prev.next.next;
        }

        private int getKey(int key) {
            return Integer.hashCode(key) % nodes.length;
        }

        private Node find(Node bucket, int key) {
            Node node = bucket;
            Node prev = null;
            while (node != null && node.key != key) {
                prev = node;
                node = node.next;
            }
            return prev;
        }

        class Node {
            int key;
            int val;
            Node next;

            Node(int k, int v) {
                key = k;
                val = v;
            }
        }
    }
}
