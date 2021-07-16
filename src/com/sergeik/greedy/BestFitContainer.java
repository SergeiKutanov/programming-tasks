package com.sergeik.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BestFitContainer {

    public static void main(String[] args) {
        assert 2 == solution(new int[] {2,2,2}, new int[][] {
                {1,1,1},
                {3,5,4},
                {2,2,3},
                {4,5,6}
        });
    }

    private static int solution(int[] box, int[][] parcel) {
        Arrays.sort(box);
        for (int[] parc: parcel)
            Arrays.sort(parc);

        int res = -1;
        int gap = Integer.MAX_VALUE;
        for (int i = 0; i < parcel.length; i++) {
            int[] parc = parcel[i];
            if (box[0] <= parc[0] && box[1] <= parc[0] && box[1] <= parc[2]) {
                int cGap = parc[0] * parc[1] * parc[2] - box[0] * box[1] * box[2];
                if (cGap < gap) {
                    res = i;
                    gap = cGap;
                }
            }
        }
        return res;
    }

}
