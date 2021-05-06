package com.sergeik.linkedlists;

import java.util.HashMap;
import java.util.Map;

public class CopyWithRandomPointer {

    public static void main(String[] args) {

        Map<Integer, Node> map = new HashMap<>();
        map.put(0, new Node(7));
        map.put(1, new Node(13));
        map.put(2, new Node(11));
        map.put(3, new Node(10));
        map.put(4, new Node(1));

        map.get(1).random = map.get(0);
        map.get(2).random = map.get(4);
        map.get(3).random = map.get(2);
        map.get(4).random = map.get(0);

        map.get(0).next = map.get(1);
        map.get(1).next = map.get(2);
        map.get(2).next = map.get(3);
        map.get(3).next = map.get(4);

        Node deepCopy = solution(map.get(0));
        assert compareLists(map.get(0), deepCopy);

    }

    private static Node solution(Node head) {
        if (head == null)
            return null;
        Node cent = new Node(Integer.MIN_VALUE);
        Node newHead = new Node(Integer.MIN_VALUE);
        newHead.next = cent;

        Map<Node, Node> oldNewMap = new HashMap<>();

        while (head != null) {
            Node nodeCopy;
            if (oldNewMap.containsKey(head)) {
                nodeCopy = oldNewMap.get(head);
            } else {
                nodeCopy = new Node(head.val);
                oldNewMap.put(head, nodeCopy);
            }
            if (head.random != null) {
                Node randomCopy;
                if (oldNewMap.containsKey(head.random)) {
                    randomCopy = oldNewMap.get(head.random);
                } else {
                    randomCopy = new Node(head.random.val);
                    oldNewMap.put(head.random, randomCopy);
                }
                nodeCopy.random = randomCopy;
            }
            cent.next = nodeCopy;
            cent = cent.next;
            head = head.next;
        }

        return newHead.next.next;
    }

    private static boolean compareLists(Node l1, Node l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val)
                return false;
            if (l1.equals(l2))
                return false;
            if (l1.random != null && l2.random != null && (l1.random.val != l2.random.val || l1.random.equals(l2.random)))
                return false;
            if (l1.random != null ^ l2.random != null)
                return false;
        }
        return true;
    }

}
