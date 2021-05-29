package com.sergeik.strings;

/**
 * You are given a string num, representing a large integer, and an integer k.
 *
 * We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num.
 * There can be many wonderful integers. However, we only care about the smallest-valued ones.
 *
 * For example, when num = "5489355142":
 * The 1st smallest wonderful integer is "5489355214".
 * The 2nd smallest wonderful integer is "5489355241".
 * The 3rd smallest wonderful integer is "5489355412".
 * The 4th smallest wonderful integer is "5489355421".
 * Return the minimum number of adjacent digit swaps that needs to be applied to num to reach
 * the kth smallest wonderful integer.
 *
 * The tests are generated in such a way that kth smallest wonderful integer exists.
 */
public class MinimumAdjacentSwapsToReachKthSmallestNumber {

    public static void main(String[] args) {
        /*
        5489355142
        The 1st smallest wonderful integer is "5489355214".
        The 2nd smallest wonderful integer is "5489355241".
        The 3rd smallest wonderful integer is "5489355412".
        The 4th smallest wonderful integer is "5489355421".

        54321


        The 4th smallest wonderful number is "5489355421". To get this number:
        - Swap index 7 with index 8: "5489355142" -> "5489355412"
        - Swap index 8 with index 9: "5489355412" -> "5489355421"

         */
        assert 2 == solution("5489355142", 4);
    }

    private static int solution(String num, int k) {
        char[] nums = num.toCharArray();
        while (k-- > 0)
            findNextPerm(nums);
        return countAdjSwaps(num.toCharArray(), nums);
    }

    private static void findNextPerm(char[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                reverse(nums, i);
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, i - 1, j);
                        return;
                    }
                }
            }
        }
        reverse(nums, 0);
    }

    private static int countAdjSwaps(char[] nums1, char[] nums2) {
        int i = 0, j = 0;
        int count = 0;

        while (i < nums1.length) {
            j = i;

            while (nums1[j] != nums2[i]) {
                j += 1;
            }
            while (i < j) {
                swap(nums1, j, j - 1);
                j -= 1;
                count++;
            }
            i++;
        }
        return count;
    }

    private static void reverse(char[] nums, int i) {
        int j = nums.length - 1;
        while (j > i) {
            swap(nums, j, i);
            j--;
            i++;
        }
    }

    private static void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

}
