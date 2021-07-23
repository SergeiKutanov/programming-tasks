package com.sergeik.arrays;

/**
 * Given an array of integers arr.
 *
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 *
 * Let's define a and b as follows:
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 *
 * Return the number of triplets (i, j and k) Where a == b.
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXor {

    public static void main(String[] args) {
        assert 4 == solution(new int[] {2,3,1,6,7});
    }

    private static int solution(int[] arr) {
        int[] prefix = new int[arr.length + 1];
        for (int i = 1; i < prefix.length; i++)
            prefix[i] = prefix[i - 1] ^ arr[i - 1];

        int res = 0;
        for (int i = 1; i < prefix.length - 1; i++) {
            for (int j = i + 1; j < prefix.length; j++)
                if ((prefix[j] ^ prefix[i - 1]) == 0)
                    res += j - i;
        }
        return res;
    }

}
