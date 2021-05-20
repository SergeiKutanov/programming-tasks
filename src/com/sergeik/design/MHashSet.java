package com.sergeik.design;

public class MHashSet {

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);
        myHashSet.add(2);
        assert myHashSet.contains(1);
        assert !myHashSet.contains(3);
        myHashSet.add(2);
        assert myHashSet.contains(2);
        myHashSet.remove(2);
        assert !myHashSet.contains(2);

        /*
        ["MyHashSet","add","remove","add","remove","remove","add","add","add","add","remove"]
        [[],         [9],   [19],   [14],   [19],   [9],    [0],    [3], [4],  [0], [9]]
         */
        myHashSet = new MyHashSet();
        myHashSet.add(9);
        myHashSet.remove(19);
        myHashSet.add(14);
        myHashSet.remove(19);
        myHashSet.remove(9);
        myHashSet.add(0);
        myHashSet.add(3);
        myHashSet.add(4);
        myHashSet.add(0);
        myHashSet.remove(9);
    }

    static class MyHashSet {

        private Node[] nodes = new Node[10000];

        /** Initialize your data structure here. */
        public MyHashSet() {

        }

        public void add(int key) {
            int hKey = getKey(key);
            if (nodes[hKey] == null)
                nodes[hKey] = new Node(0); //centeneal node
            Node node = find(nodes[hKey], key);
            if (node.next == null)
                node.next = new Node(key);
        }

        public void remove(int key) {
            int hKey = getKey(key);
            if (nodes[hKey] == null)
                return;
            Node node = find(nodes[hKey], key);
            if (node.next == null)
                return;
            node.next = node.next.next;
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int hKey = getKey(key);
            if (nodes[hKey] == null)
                return false;
            Node node = find(nodes[hKey], key);
            return node.next != null;
        }

        private int getKey(int val) {
            return val % nodes.length;
        }

        private Node find(Node centNode, int key) {
            Node node = centNode.next;
            Node prev = centNode;
            while (node != null && node.val != key) {
                prev = node;
                node = node.next;
            }
            return prev;
        }

        class Node {
            int val;
            Node next;

            Node(int val) {
                this.val = val;
            }

        }
    }


}
