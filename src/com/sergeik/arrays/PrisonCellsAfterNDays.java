package com.sergeik.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * There are 8 prison cells in a row and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.
 *
 * You are given an integer array cells where cells[i] == 1 if the ith cell is occupied and cells[i] == 0 
 * if the ith cell is vacant, and you are given an integer n.
 *
 * Return the state of the prison after n days (i.e., n such changes described above).
 */
public class PrisonCellsAfterNDays {

    public static void main(String[] args) {
        assert Arrays.equals(
                prisonAfterNDays(new int[]{0,1,0,1,1,0,0,1}, 7),
                new int[]{0, 0, 1, 1, 0, 0, 0, 0}
        );
        assert Arrays.equals(
                prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000),
                new int[]{0,0,1,1,1,1,1,0}
        );
    }

    public static int[] prisonAfterNDays(int[] cells, int n) {
        if (cells == null || cells.length == 0 || n <= 0)
            return cells;

        boolean hasCycle = false;
        int cycle = 0;
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int[] nextDay = calcDay(cells);
            String key = Arrays.toString(nextDay);
            if (!seen.contains(key)) {
                seen.add(key);
                cycle++;
            } else {
                hasCycle = true;
                break;
            }
            cells = nextDay;
        }

        if (hasCycle) {
            n %= cycle;
            for (int i = 0; i < n; i++) {
                cells = calcDay(cells);
            }
        }
        return cells;
    }

    private static int[] calcDay(int[] cells) {
        int[] tmp = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++)
            tmp[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        return tmp;
    }

}
