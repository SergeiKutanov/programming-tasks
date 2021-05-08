package com.sergeik.linkedlists;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 */
public class IntersectionOfTwoLL {

    public static void main(String[] args) {
        ListNode headA = ListHelper.buildFromArray(new int[]{4,1});
        ListNode headB = ListHelper.buildFromArray(new int[]{5,6,1});
        ListNode common = ListHelper.buildFromArray(new int[]{8,4,5});
        ListNode tail = headA;
        while (tail.next != null)
            tail = tail.next;
        tail.next = common;
        tail = headB;
        while (tail.next != null)
            tail = tail.next;
        tail.next = common;
        assert common.equals(getIntersectionNodeWithSet(headA, headB));
        assert common.equals(getIntersectionNodeWithStack(headA, headB));
        assert common.equals(getIntersectionWithLength(headA, headB));

        headA = ListHelper.buildFromArray(new int[]{0,1,2,3,4,5,65});
        headB = ListHelper.buildFromArray(new int[]{0,1,2,3,4});
        assert null == getIntersectionNodeWithSet(headA, headB);
        assert null == getIntersectionNodeWithStack(headA, headB);
        assert null == getIntersectionWithLength(headA, headB);
    }

    private static ListNode getIntersectionWithLength(ListNode headA, ListNode headB) {
        int lenA = 0;
        ListNode head = headA;
        while (head != null) {
            lenA++;
            head = head.next;
        }

        int lenB = 0;
        head = headB;
        while (head != null) {
            lenB++;
            head = head.next;
        }

        ListNode longer = lenA > lenB ? headA : headB;
        ListNode shorter = longer.equals(headA) ? headB : headA;
        int diff = Math.abs(lenA - lenB);
        while (diff > 0) {
            longer = longer.next;
            diff--;
        }

        while (longer != null && shorter != null) {
            if (longer.equals(shorter))
                return longer;
            longer = longer.next;
            shorter = shorter.next;
        }
        return null;
    }

    private static ListNode getIntersectionNodeWithStack(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = new Stack<>();
        while (headA != null) {
            stackA.add(headA);
            headA = headA.next;
        }
        Stack<ListNode> stackB = new Stack<>();
        while (headB != null) {
            stackB.add(headB);
            headB = headB.next;
        }

        ListNode prev = null;
        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            ListNode nodeA = stackA.pop();
            ListNode nodeB = stackB.pop();
            if (!nodeA.equals(nodeB))
                return prev;
            prev = nodeA;
        }
        return prev;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    // 1->2->3->4
    // prev = 1->null; head = 2->3->4
    // prev = 2->1->null; head 3->4
    // prev = 3->2->1->null; head 4
    // prev = 4->3->2->1; head = null

    private static ListNode getIntersectionNodeWithSet(ListNode headA, ListNode headB) {
        Set<ListNode> nodeSet = new HashSet<>();
        while (headA != null && headB != null) {
            if (nodeSet.contains(headA))
                return headA;
            if (nodeSet.contains(headB))
                return headB;
            if (headA.equals(headB))
                return headA;
            nodeSet.add(headA);
            nodeSet.add(headB);
            headA = headA.next;
            headB = headB.next;
        }
        ListNode tail = headA == null ? headB : headA;
        while (tail != null) {
            if (nodeSet.contains(tail))
                return tail;
            nodeSet.add(tail);
            tail = tail.next;
        }
        return null;
    }

}
