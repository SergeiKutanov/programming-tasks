package com.sergeik.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown
 */
public class PascalTriangleII {

    public static void main(String[] args) {
        assert Arrays.equals(
                new Integer[] {1,3,3,1},
                solution(3).toArray(new Integer[0])
        );

        assert Arrays.equals(
                new Integer[] {1,4,6,4,1},
                solution(4).toArray(new Integer[0])
        );
    }

    private static List<Integer> solution(int rowIndex) {
        return buildRow(rowIndex);
    }

    private static List<Integer> buildRow(int rowIndex) {
        if (rowIndex == 0)
            return Arrays.asList(1);
        List<Integer> prevRow = buildRow(rowIndex - 1);
        List<Integer> newRow = new LinkedList<>();
        newRow.add(1);
        int i = 1;
        while (i < prevRow.size()) {
            newRow.add(prevRow.get(i) + prevRow.get(i - 1));
            i++;
        }
        newRow.add(1);
        return newRow;
    }

}
