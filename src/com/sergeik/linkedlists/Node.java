package com.sergeik.linkedlists;

public class Node extends ListNode {

    Node random;
    Node next;

    Node(int x) {
        super(x);
    }

    Node(int x, Node r) {
        super(x);
        random = r;
    }
}
