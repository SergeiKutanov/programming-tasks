package com.sergeik.arrays;

import java.util.*;

public class RelativeRanks {

    public static void main(String[] args) {
        assert Arrays.equals(new String[]{
                "Gold Medal", "5", "Bronze Medal", "Silver Medal", "4"
        }, solution(new int[]{10,3,8,9,4}));
    }

    private static String[] solution(int[] score) {
        String[] ranks = new String[score.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            map.put(score[i], i);
        }
        Arrays.sort(score);
        int rankOrder = 1;
        for (int i = score.length - 1; i >= 0; i--) {
            int index = map.get(score[i]);
            String rank;
            if (rankOrder == 1)
                rank = "Gold Medal";
            else if (rankOrder == 2)
                rank = "Silver Medal";
            else if (rankOrder == 3)
                rank = "Bronze Medal";
            else
                rank = Integer.toString(rankOrder);
            rankOrder++;
            ranks[index] = rank;
        }
        return ranks;
    }

}
