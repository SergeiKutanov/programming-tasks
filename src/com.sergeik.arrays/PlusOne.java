package com.sergeik.arrays;

import java.util.Arrays;
import java.util.LinkedList;

public class PlusOne {

    public static void main(String[] args) {
        int[] digits = {1, 2, 3};
        assert Arrays.equals(new int[] {1, 2, 4}, solution(digits));
        assert Arrays.equals(new int[] {1, 2, 4}, inlineSolution(digits));

        digits = new int[]{4, 3, 2, 1};
        assert Arrays.equals(new int[] {4, 3, 2, 2}, solution(digits));
        assert Arrays.equals(new int[] {4, 3, 2, 2}, inlineSolution(digits));

        digits = new int[] {0};
        assert Arrays.equals(new int[] {1}, solution(digits));
        assert Arrays.equals(new int[] {1}, inlineSolution(digits));

        digits = new int[]{9, 9, 9};
        assert Arrays.equals(new int[] {1, 0, 0, 0}, solution(digits));
        assert Arrays.equals(new int[] {1, 0, 0, 0}, inlineSolution(digits));
    }

    /**
     * Not optimal solution since will iterate over the whole array in any case
     * Running time O(n)
     * Memory O(n)
     * @param digits
     * @return
     */
    private static int[] solution(int[] digits) {
        int carryOver = 1;
        int current = digits.length - 1;
        LinkedList<Integer> res = new LinkedList<>();
        while (current >= 0) {
            int currentValue = digits[current] + carryOver;
            if (currentValue == 10) {
                carryOver = 1;
                currentValue = 0;
            } else {
                carryOver = 0;
            }
            res.addFirst(currentValue);
            current--;
        }
        if (carryOver > 0) {
            res.addFirst(1);
        }
        int result[] = new int[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    /**
     * Keeps the modification inline
     * breaks out of loop if no carry over
     * Creates new array only in case we need to increase array sie due to the leftmost carry over
     *
     * The worst case running time is O(n) but it will only happen if we get the leftmost carry over
     * Memory - O(n), will create new array only in case of the leftmost carry over
     *
     * @param digits
     * @return
     */
    private static int[] inlineSolution(int[] digits) {
        int carryOver = 1;
        int current = digits.length - 1;
        while (current >= 0) {
            int value = digits[current] + carryOver;
            if (value == 10) {
                value = 0;
            } else {
                carryOver = 0;
            }
            digits[current] = value;
            if (carryOver == 0) {
                break;
            }
            current--;
        }
        //got carry over from the highest digit
        if (carryOver == 1) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for (int i = 1; i < res.length; i++) {
                res[i] = digits[i-1];
            }
            return res;
        }
        return digits;
    }

}
