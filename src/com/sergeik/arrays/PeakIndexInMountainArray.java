package com.sergeik.arrays;

/**
 * Let's call an array arr a mountain if the following properties hold:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * Given an integer array arr that is guaranteed to be a mountain, return any i such that arr[0] <
 * arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 */
public class PeakIndexInMountainArray {

    public static void main(String[] args) {
        assert 2 == solution(new int[] {3,4,5,1});
        assert 1 == solution(new int[] {0,1,0});
        assert 1 == solution(new int[] {0,10,5,2});
    }

    private static int solution(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] < arr[middle + 1])
                left = middle + 1;
            else
                right = middle;
        }
        return left;
    }

}
