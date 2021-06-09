package com.sergeik.dynamic;

import java.util.LinkedList;
import java.util.List;

public class AmazonCompartments {

    public static void main(String[] args) {
        solution(
                "|||||******|**|****|******|*|*||*|******|*||**|***|***|**||*|**|***|*|*|**||***|******|*|||*****||||",
                new LinkedList<>(),
                new LinkedList<>()
        );
    }

    private static List<Integer> solution(String s, List<Integer> start, List<Integer> end) {

        int[] dp = new int[s.length()];

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|')
                dp[i] = count;
            else
                count++;
        }

        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < start.size(); i++) {
            int st = start.get(i) - 1;
            int ed = end.get(i) - 1;
            while (st < ed && s.charAt(st) != '|')
                st++;
            while (st < ed && s.charAt(ed) != '|')
                ed--;
            res.add(dp[ed] - dp[st]);
        }

        return res;
    }

}
