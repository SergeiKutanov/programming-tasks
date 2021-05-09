package com.sergeik.linkedlists;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {

    static ListNode buildFromArray(int[] values) {
        if (values.length < 1) return null;
        ListNode head = new ListNode(values[0]);
        ListNode prev = head;
        for (int i = 1; i < values.length; i++) {
            prev.next = new ListNode(values[i]);
            prev = prev.next;
        }
        return head;
    }

    static int[] toArray(ListNode head) {
        List<Integer> res = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            res.add(node.val);
            node = node.next;
        }
        return arrayListOfIntsToArr(res);
    }

    static ListNode getNth(ListNode head, int index) {
        ListNode node = head;
        int currentIndex = 0;
        while (currentIndex < index && node != null) {
            node = node.next;
            currentIndex++;
        }
        return node;
    }

    private static int[] arrayListOfIntsToArr(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static boolean compareIntLists(List<Integer> l1, List<Integer> l2) {
        if (l1.size() != l2.size())
            return false;
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i) != l2.get(i))
                return false;
        }
        return true;
    }

}
