package com.sergeik.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PathInZigZagLabeledTree {

    public static void main(String[] args) {
        assert Arrays.equals(new Integer[] {1,3,4,15,16}, solution(16).toArray());
        assert Arrays.equals(new Integer[] {1,2,6,10,26}, pathInZigZagTree(26).toArray());
        assert Arrays.equals(new Integer[] {1,3,4,14}, solution(14).toArray());
    }

    private static List<Integer> solution(int label) {
        LinkedList<Integer> res = new LinkedList<>();
        int level = 1, levelMax = 2;
        while (levelMax <= label) {
            levelMax <<= 1;
            level++;
        }
        level--;

        boolean straight = true;
        while (label > 0) {
            if (straight)
                res.addFirst(label);
            else
                res.addFirst((1 << level) + (1 << level + 1) - label - 1);
            straight = !straight;
            label /= 2;
            level--;
        }
        return res;
    }

    public static List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        while (label > 0) {
            res.add(0, label);
            label /= 2;
        }
        for (int i = res.size() - 2; i > 0; i -= 2)
            res.set(i, (1 << i) + (1 << i + 1) - res.get(i) - 1);
        return res;
    }

}
