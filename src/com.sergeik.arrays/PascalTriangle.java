package com.sergeik.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it
 */
public class PascalTriangle {

    public static void main(String[] args) {
        List<List<Integer>> expected = new LinkedList<>();
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(1, 1));
        expected.add(Arrays.asList(1, 2, 1));
        expected.add(Arrays.asList(1, 3, 3, 1));
        expected.add(Arrays.asList(1, 4, 6, 4, 1));
        List<List<Integer>> result = solution(5);
        assert compareLists(expected, result);
    }

    private static List<List<Integer>> solution(int numRows) {
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            buildRow(i, res);
        }
        return res;
    }

    private static void buildRow(int level, List<List<Integer>> res) {
        if (level == 0) {
            res.add(Arrays.asList(1));
        } else if (level == 1) {
            res.add(Arrays.asList(1, 1));
        } else {
            List<Integer> levelList = new LinkedList<>();
            levelList.add(1);
            List<Integer> prevLevel = res.get(level - 1);
            for (int i = 1; i < level ; i++) {
                int v = prevLevel.get(i - 1) + prevLevel.get(i);
                levelList.add(v);
            }
            levelList.add(1);
            res.add(levelList);
        }
    }

    private static boolean compareLists(List<List<Integer>> l1, List<List<Integer>> l2) {
        if (l1.size() != l2.size())
            return false;
        for (int i = 0; i < l1.size(); i++) {
            List<Integer> l11 = l1.get(i);
            List<Integer> l22 = l2.get(i);
            if (l11.size() != l22.size()) {
                return false;
            }
            for (int j = 0; j < l11.size(); j++) {
                if (l11.get(j) != l22.get(j))
                    return false;
            }
        }
        return true;
    }

}
