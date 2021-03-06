package com.sergeik.utils;

public class Search {

    static class Binary {

        public static void main(String[] args) {
            assert -1 == solutionSmaller(new int[] {1,2,3,4,5,6,7}, 0);
            assert 6 == solutionSmaller(new int[] {1,2,3,4,5,6,7}, 8);
            assert 4 == solutionSmaller(new int[] {1,2,3,4,5,6,7}, 6);
            assert 2 == solutionSmaller(new int[] {1,2,3,4,5,6,7}, 4);

            assert 2 == solutionGreater(new int[] {1,2,3,3,4,5,6,7}, 2);
            assert 4 == solutionGreater(new int[] {1,2,3,3,4,5,6,7}, 3);
            assert 0 == solutionGreater(new int[] {1,2,3,4,5,6,7}, 0);
            assert 3 == solutionGreater(new int[] {1,2,3,4,5,6,7}, 3);
            assert 7 == solutionGreater(new int[] {1,2,3,4,5,6,7}, 7);

        }

        /**
         * Finds index of first element which value is strictly greater than n
         * @param nums
         * @param n
         * @return
         */
        private static int solutionGreater(int[] nums, int n) {
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int m = (l + r) / 2;
                if (nums[m] <= n) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return l;
        }

        /**
         * Finds index of last element that is strictly smaller then n
         * @param nums
         * @param n
         * @return
         */
        private static int solutionSmaller(int[] nums, int n) {
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int m = (l + r) / 2;
                if (nums[m] >= n) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return r;
        }

    }

}
