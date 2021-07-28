package com.sergeik.sortsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindPositiveIntegerSolutionForAGivenEquation {

    public static void main(String[] args) {
        List<List<Integer>> exp, res;
        exp = new LinkedList<>();
        exp.add(Arrays.asList(1,4));
        exp.add(Arrays.asList(2,3));
        exp.add(Arrays.asList(3,2));
        exp.add(Arrays.asList(4,1));
        res = solution(new CustomFunction(), 5);
        for (int i = 0; i < exp.size(); i++)
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j) == res.get(i).get(j);
    }

    public static List<List<Integer>> solution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int x = 1; x < 1002; x++) {
            int left = 1, right = 1002;
            while (left < right) {
                int middle = (left + right) / 2;
                int val = customfunction.f(x, middle);
                if (val > z)
                    right = middle;
                else if (val < z)
                    left = middle + 1;
                else {
                    res.add(Arrays.asList(x, middle));
                    break;
                }

            }
        }

        return res;
    }

    public static List<List<Integer>> bruteSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int x = 1; x < 1002; x++)
            for (int y = 1; y < 1002; y++) {
                int val = customfunction.f(x, y);
                if (val == z)
                    res.add(Arrays.asList(x, y));
                else if (val > z)
                    break;
            }
        return res;
    }

    static class CustomFunction {

        public int f(int x, int y) {
            return x + y;
        }

    }

}
