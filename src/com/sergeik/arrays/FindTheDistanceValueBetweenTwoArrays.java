package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.
 *
 * The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j]
 * where |arr1[i]-arr2[j]| <= d.
 */
public class FindTheDistanceValueBetweenTwoArrays {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {2,1,100,3}, new int[] {-5,-2,10,-3,7}, 6);
        assert 2 == solution(new int[] {1,4,2,3}, new int[] {-4,-3,6,10,20,30}, 3);
        assert 2 == solution(new int[] {4,5,8}, new int[] {10,9,1,8}, 2);
    }

    private static int solution(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int res = arr1.length;
        for (int n: arr1) {
            int min = n - d;
            int max = n + d;
            int ceil = findCeil(arr2, min);
            if (ceil < arr2.length && arr2[ceil] <= max)
                res--;
        }
        return res;
    }

    private static int findCeil(int[] arr, int n) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] < n)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

}
