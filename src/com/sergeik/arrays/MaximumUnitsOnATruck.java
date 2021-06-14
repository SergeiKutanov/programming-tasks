package com.sergeik.arrays;

import java.util.Arrays;

/**
 * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes,
 * where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 *
 * numberOfBoxesi is the number of boxes of type i.
 * numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck.
 * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 *
 * Return the maximum total number of units that can be put on the truck.
 */
public class MaximumUnitsOnATruck {

    public static void main(String[] args) {
        assert 8 == solution(new int[][]{{1,3}, {2,2}, {3,1}}, 4);
        assert 91 == solution(new int[][]{{5,10},{2,5},{4,7},{3,9}}, 10);
    }

    private static int solution(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int units = 0, boxes = 0, idx = 0;
        while (boxes < truckSize && idx < boxTypes.length) {
            int boxesToTake = Math.min(
                    truckSize - boxes,
                    boxTypes[idx][0]
            );
            units += boxTypes[idx][1] * boxesToTake;
            boxes += boxesToTake;
            idx++;
        }
        return units;
    }

}
