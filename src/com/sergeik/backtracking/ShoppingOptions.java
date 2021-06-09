package com.sergeik.backtracking;

import java.util.*;

public class ShoppingOptions {

    public static void main(String[] args) {
        assert 3 == solution(
                Arrays.asList(2,3),
                Arrays.asList(4),
                Arrays.asList(2,3),
                Arrays.asList(1,2),
                10
        );
    }

    private static int solution(
            List<Integer> priceOfJeans,
            List<Integer> priceOfShoes,
            List<Integer> priceOfSkirts,
            List<Integer> priceOfTops,
            int budget
    ) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int count = 0;

        for (int jeans: priceOfJeans) {
            for (int sh: priceOfShoes) {
                int sum = jeans + sh;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int sk: priceOfSkirts) {
            for (int t: priceOfTops) {
                int price = budget - (sk + t);
                for (int pr: map.keySet()) {
                    if (pr <= price) {
                        count += map.get(pr);
                    }
                }
            }
        }

        return count;
    }

}
