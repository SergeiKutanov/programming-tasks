package com.sergeik.greedy;

/**
 * You would like to make dessert and are preparing to buy the ingredients. You have n ice cream base flavors
 * and m types of toppings to choose from. You must follow these rules when making your dessert:
 *
 * There must be exactly one ice cream base.
 * You can add one or more types of topping or have no toppings at all.
 * There are at most two of each type of topping.
 * You are given three inputs:
 *
 * baseCosts, an integer array of length n, where each baseCosts[i] represents
 * the price of the ith ice cream base flavor.
 * toppingCosts, an integer array of length m, where each toppingCosts[i] is the price of one of the ith topping.
 * target, an integer representing your target price for dessert.
 * You want to make a dessert with a total cost as close to target as possible.
 *
 * Return the closest possible cost of the dessert to target. If there are multiple, return the lower one.
 */
public class ClosestDessertCost {

    static private int res = 0;

    public static void main(String[] args) {

        assert 13 == solution(new int[]{4}, new int[]{9}, 9);

        assert 10 == solution(
                new int[] {10},
                new int[] {1},
                1
        );

        assert 8 == solution(
                new int[] {3,10},
                new int[] {2,5},
                9
        );

        assert 10 == solution(
                new int[] {1,7},
                new int[] {3,4},
                10
        );

        assert 17 == solution(
                new int[] {2,3},
                new int[] {4,5,100},
                18
        );
    }

    private static int solution(int[] baseCosts, int[] toppingCosts, int target) {
        res = baseCosts[0];
        for (int n: baseCosts)
            helper(n, toppingCosts, 0, target);
        return res;
    }

    private static void helper(int current, int[] toppings, int index, int target) {
        if (Math.abs(target - current) < Math.abs(target - res) || Math.abs(target - current) == Math.abs(target - res) && current < res)
            res = current;
        if (index == toppings.length || current >= target)
            return;
        helper(current, toppings, index + 1, target);
        helper(current + toppings[index], toppings, index + 1, target);
        helper(current + toppings[index] * 2, toppings, index + 1, target);
    }

}
