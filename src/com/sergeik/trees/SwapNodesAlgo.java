package com.sergeik.trees;

import java.util.*;

public class SwapNodesAlgo {

    public static void main(String[] args) {
        List<List<Integer>> indexes, res, exp;
        List<Integer> queries;

        queries = new ArrayList<>();
        indexes = new ArrayList<>();
        indexes.add(Arrays.asList(11));
        indexes.add(Arrays.asList(2,3));
        indexes.add(Arrays.asList(4,-1));
        indexes.add(Arrays.asList(5,-1));
        indexes.add(Arrays.asList(6,-1));
        indexes.add(Arrays.asList(7,8));
        indexes.add(Arrays.asList(-1,9));
        indexes.add(Arrays.asList(-1,-1));
        indexes.add(Arrays.asList(10,11));
        indexes.add(Arrays.asList(-1,-1));
        indexes.add(Arrays.asList(-1,-1));
        indexes.add(Arrays.asList(-1,-1));
        queries.add(2);
        queries.add(2);
        queries.add(4);

        res = solution(indexes, queries);
        exp = new ArrayList<>();
        exp.add(Arrays.asList(2,9,6,4,1,3,7,5,11,8,10));
        exp.add(Arrays.asList(2,6,9,4,1,3,7,5,10,8,11));
        for (int i = 0; i < exp.size(); i++)
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));
    }

    private static List<List<Integer>> solution(List<List<Integer>> indexes, List<Integer> queries) {
        Node root = buildTree(indexes);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 1; i < queries.size(); i++) {
            swap(root, queries.get(i));
            List<Integer> trav = new LinkedList<>();
            inOrderTrav(root, trav);
            res.add(trav);
        }
        return res;
    }

    private static void inOrderTrav(Node node, List<Integer> data) {
        if (node == null)
            return;
        inOrderTrav(node.left, data);
        data.add(node.data);
        inOrderTrav(node.right, data);
    }

    private static void swap(Node root, int k) {
        Stack<Node> cLevel = new Stack<>();
        Stack<Node> nLevel = new Stack<>();
        int level = 1;
        Node tmp;
        cLevel.push(root);
        while (!cLevel.isEmpty()) {
            tmp = cLevel.pop();
            if (tmp.left != null)
                nLevel.push(tmp.left);
            if (tmp.right != null)
                nLevel.push(tmp.right);
            if (level % k == 0) {
                Node n = tmp.left;
                tmp.left = tmp.right;
                tmp.right = n;
            }
            if (cLevel.isEmpty()) {
                Stack<Node> t = cLevel;
                cLevel = nLevel;
                nLevel = t;
                level++;
            }
        }
    }

    private static Node buildTree(List<List<Integer>> tree) {
        Node root = new Node(1);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        for (int i = 1; i < tree.size(); i++) {
            Node left = null, right = null;
            if (tree.get(i).get(0) != -1)
                left = new Node(tree.get(i).get(0));
            if (tree.get(i).get(1) != -1)
                right = new Node(tree.get(i).get(1));
            Node parent = q.poll();
            parent.left = left;
            parent.right = right;

            if (left != null)
                q.offer(left);
            if (right != null)
                q.offer(right);
        }
        return root;
    }

    static class Node {
        int data;
        Node left, right;
        Node(int d) {
            data = d;
            left = null;
            right = null;
        }
    }

}
