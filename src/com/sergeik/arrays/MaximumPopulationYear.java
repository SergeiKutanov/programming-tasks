package com.sergeik.arrays;


/**
 *
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death
 * years of the ith person.
 *
 * The population of some year x is the number of people alive during that year. The ith person is counted in
 * year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted
 * in the year that they die.
 *
 * Return the earliest year with the maximum population.
 *
 * 1 <= logs.length <= 100
 * 1950 <= birthi < deathi <= 2050
 */
public class MaximumPopulationYear {

    public static void main(String[] args) {
        assert 1960 == solution(new int[][]{
                {1950, 1961},
                {1960, 1971},
                {1970, 1981}
        });
    }

    private static int solution(int[][] logs) {
        int[] years = new int[2051];
        int count = 0;
        for (int[] log: logs) {
            years[log[0]]++;
            years[log[1]]--;
        }

        for (int i = 1950; i < years.length; i++) {
            years[i] += years[i - 1];
            count = years[i] > years[count] ? i : count;
        }

        return count;
    }

}
