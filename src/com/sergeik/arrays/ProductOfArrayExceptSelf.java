package com.sergeik.arrays;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        assert Arrays.equals(new int[]{24,12,8,6}, solution(new int[]{1,2,3,4}));
        assert Arrays.equals(new int[]{24,12,8,6}, anotherSolution(new int[]{1,2,3,4}));
        assert Arrays.equals(new int[]{0,0,9,0,0}, solution(new int[]{-1,1,0,-3,3}));
        assert Arrays.equals(new int[]{0,0,9,0,0}, anotherSolution(new int[]{-1,1,0,-3,3}));
    }

    private static int[] solution(int[] nums) {
        if (nums.length <= 1)
            return new int[]{};

        int totalProduct = nums[0];
        int zeros = 0;
        int zeroIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
                zeroIndex = i;
            } else {
                totalProduct *= nums[i];
            }
        }

        int[] res = new int[nums.length];

        if (zeros > 1) {
            return res;
        } else if (zeros == 1) {
            res[zeroIndex] = totalProduct;
        } else {
            for (int i = 0; i < nums.length; i++) {
                res[i] = totalProduct / nums[i];
            }
        }

        return res;
    }

    private static int[] anotherSolution(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

}
