package com.sergeik.sortsearch;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius
 * to warm all the houses.
 *
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 *
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters
 * so that those heaters could cover all houses.
 *
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 */
public class Heaters {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {1,2,3,4}, new int[] {1,4});
        assert 3 == solution(new int[] {1,5}, new int[] {2});
    }

    private static int solution(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int maxRadius = 0;
        for (int house: houses) {
            int heaterIdx = Arrays.binarySearch(heaters, house);
            if (heaterIdx < 0) {
                heaterIdx = ~heaterIdx;
                int floor = (heaterIdx > 0) ? house - heaters[heaterIdx - 1] : Integer.MAX_VALUE;
                int ceil = (heaterIdx < heaters.length) ? heaters[heaterIdx] - house : Integer.MAX_VALUE;
                maxRadius = Math.max(
                        maxRadius,
                        Math.min(floor, ceil)
                );
            }
        }
        return maxRadius;
    }

    private static int binarySearchSolution(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int maxRadius = 0;
        for (int house: houses) {
            int closestHeater = find(heaters, house);
            maxRadius = Math.max(maxRadius, closestHeater);
        }
        return maxRadius;
    }

    private static int find(int[] heaters, int house) {
        int left = 0, right = heaters.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (heaters[mid] >= house)
                right = mid;
            else
                left = mid + 1;
        }
        int closestHeater = Math.abs(house - heaters[left]);
        if (left > 0) {
            closestHeater = Math.min(
                    closestHeater,
                    Math.abs(house - heaters[left - 1])
            );
        }
        return closestHeater;
    }


    private static int treeSetSolution(int[] houses, int[] heaters) {
        TreeSet<Integer> heatersSet = new TreeSet<>();
        for (int n: heaters)
            heatersSet.add(n);
        int maxRadius = 0;
        for (int house: houses) {
            Integer heaterFloor = heatersSet.floor(house);
            Integer heaterCeil = heatersSet.ceiling(house);
            int closestHeater = Integer.MAX_VALUE;
            if (heaterFloor != null)
                closestHeater = Math.min(closestHeater, Math.abs(house - heaterFloor));
            if (heaterCeil != null)
                closestHeater = Math.min(closestHeater, Math.abs(house - heaterCeil));
            maxRadius = Math.max(maxRadius, closestHeater);
        }
        return maxRadius;
    }

}
