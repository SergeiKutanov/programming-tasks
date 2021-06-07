package com.sergeik.arrays;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A good meal is a meal that contains exactly two different food items with a sum of deliciousness
 * equal to a power of two.
 *
 * You can pick any two different foods to make a good meal.
 *
 * Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the i​​​​​​th​​​​​​​​ item of food,
 * return the number of different good meals you can make from this list modulo 109 + 7.
 *
 * Note that items with different indices are considered different even if they have the same deliciousness value.
 */
public class CountGoodMeals {

    public static void main(String[] args) {
        assert 4 == solution(new int[] {1,3,5,7,9});
        assert 15 == solution(new int[] {1,1,1,3,3,3,7});
    }

    private static int solution(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] powersOfTwo = new int[22];
        powersOfTwo[0] = 1;
        for (int i = 1; i < powersOfTwo.length; i++) {
            powersOfTwo[i] = 2 * powersOfTwo[i - 1];
        }

        int res = 0;
        for (int n: deliciousness) {
            for (int j = 0; j < powersOfTwo.length; j++) {
                int diff = powersOfTwo[j] - n;
                if (map.containsKey(diff)) {
                    res += map.get(diff);
                    res %= 1000000007;
                }
            }
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        return res;
    }


    private static int bruteSolution(int[] deliciousness) {
        Set<Integer> powersOfTwo = new HashSet<>();
        powersOfTwo.add(1);
        int prev = 1;
        for (int i = 1; i <= 21; i++) {
            prev *= 2;
            powersOfTwo.add(prev);
        }

        long res = 0;
        for (int i = 0; i < deliciousness.length - 1; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                if (powersOfTwo.contains(deliciousness[i] + deliciousness[j]))
                    res++;
            }
        }
        return (int) (res % 1000000007);
    }

}
