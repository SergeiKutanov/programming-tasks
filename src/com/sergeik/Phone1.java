package com.sergeik;

import java.util.HashMap;
import java.util.Map;

public class Phone1 {

    // Implement LRU Cache with two public methods get/put.

    class LRUCache {

        Map<String, Node> map;
        Node head;
        Node tail;
        int cap;
        int size;


        LRUCache(int size) {
//            if (size <= 0)
//                throw new Exception("Only positive cache size allowed");
            map = new HashMap<>();
            cap = size;
            head = new Node("", "");
            tail = new Node("", "");
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        String get(String key) {
            Node node = map.get(key);
            if (node == null)
                return null;
            node.remove();
            head.insert(node);
            return node.val;    // forgot in interview
        }

        void put(String key, String val) {
            Node node = map.get(key);
            if (node == null) {
                node = new Node(val, key);
                map.put(key, node);
                if (size == cap) {
                    Node nodeToDelete = tail.prev;
                    nodeToDelete.remove();
                    map.remove(nodeToDelete.key);
                    size--;
                }
            } else {
                node.val = val;
                node.remove();
            }
            head.insert(node);
            size++;
        }


        class Node {
            Node prev;
            Node next;
            String val;
            String key;

            Node (String v, String k) {
                val = v;
                key = k;
            }

            void remove() {
                if (this.prev != null && this.next != null) {
                    this.prev.next = this.next;
                    this.next.prev = this.prev;
                } else if (this.prev == null) {
                    this.next.prev = null;
                } else {
                    this.prev.next = null;
                }
            }

            void insert(Node node) {
                Node tmp = next;
                node.next = tmp;
                tmp.prev = node;
                this.next = node;
                node.prev = this;
            }

        }

    }




}
