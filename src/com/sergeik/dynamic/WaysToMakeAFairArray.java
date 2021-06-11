package com.sergeik.dynamic;

/**
 * You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element.
 * Notice that the index of the elements may change after the removal.
 *
 * For example, if nums = [6,1,7,4,1]:
 *
 * Choosing to remove index 1 results in nums = [6,7,4,1].
 * Choosing to remove index 2 results in nums = [6,1,4,1].
 * Choosing to remove index 4 results in nums = [6,1,7,4].
 * An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
 *
 * Return the number of indices that you could choose such that after the removal, nums is fair.
 */
public class WaysToMakeAFairArray {

    public static void main(String[] args) {
        assert 3 == solution(new int[] {1,1,1});
        assert 0 == solution(new int[] {1,2,3,4,5,6,7});
        assert 1 == solution(new int[] {2,1,6,4});

        assert 3 == twoSumsSolution(new int[] {1,1,1});
        assert 0 == twoSumsSolution(new int[] {1,2,3,4,5,6,7});
        assert 1 == twoSumsSolution(new int[] {2,1,6,4});

    }

    private static int twoSumsSolution(int[] nums) {
        int res = 0, n = nums.length, left[] = new int[2], right[] = new int[2];
        for (int i = 0; i < n; i++)
            right[i%2] += nums[i];
        for (int i = 0; i < n; i++) {
            right[i%2] -= nums[i];
            if (left[0]+right[1] == left[1]+right[0]) res++;
            left[i%2] += nums[i];
        }
        return res;
    }

    private static int solution(int[] nums) {
        if (nums.length < 2)
            return 1;

        int res = 0;
        boolean evenLength = nums.length % 2 == 0;
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        dp[2] = nums[1];
        for (int i = 3; i <= nums.length; i++) {
            dp[i] = dp[i - 2] + nums[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            int isOdd = i % 2;
            int evenPreIdx = i - 1 + isOdd;
            int evenSum = evenPreIdx >= 0 ? dp[evenPreIdx] : 0;
            evenSum -= dp[i + isOdd];
            evenSum += evenLength ? dp[nums.length] : dp[nums.length - 1];

            int oddSum = dp[i - isOdd];
            oddSum -= dp[i + 1 - isOdd];
            oddSum += evenLength ? dp[nums.length - 1] : dp[nums.length];

            if (evenSum == oddSum)
                res++;
        }

        return res;
    }

    private static int dpSsolution(int[] nums) {
        /*

                1   2   3   4   5
                1   2   4   5

                1   2   3   4   5   6   7
        dp =    1   2   4   6   9   12  16

        remove nums[0] - 2  3   4   5   6   7
        even sum 12 = 2 + 4 + 6
        odd sum 15 = 16 - 1

        remove nums[1] - 1  3   4   5   6   7
        even sum 11 = 1 + 4 + 6 - take last even sum (i = 6) + even intact (i = 1) - removed odd (i = 2)
        odd sum 15 = 3 + 5 + 7

        remove nums[2] 1    2   4   5   6   7
        even = 11
        odd = 14

        remove nums[3] 1    2   3   5   6   7
        even = 10
        odd = 14
        remove nums[4] 1    2   3   4   6   7
        even = 10
        odd = 13
        remove nums[5]  1   2   3   4   5   7
        even = 9
        odd = 13
        remove nums[6] 1    2   3   4   5   6
        odd = 9
        even = 12

        2 1 6 4
        1   6   4   - 5     6
        2   6   4   - 6     6
        2   1   4   - 6     1
        2   1   6   - 8     1


         */

        if (nums.length < 2)
            return 1;

        int res = 0;
        boolean evenLength = nums.length % 2 == 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = dp[i - 2] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            int evenSum = 0;
            int oddSum = 0;
            int evenPreIdx = i - 2 + (i % 2);

            evenSum = evenPreIdx >= 0 ? dp[evenPreIdx] : 0;
            if (i % 2 == 0) {
//                evenSum = i - 2 >= 0 ? dp[i - 2] : 0;
                evenSum -= i - 1 >= 0 ? dp[i - 1] : 0;

                oddSum = i - 1 >= 0 ? dp[i - 1] : 0;
                oddSum -= dp[i];
            } else {
//                evenSum = dp[i - 1];
                evenSum -= dp[i];

                oddSum = i - 2 >= 0 ? dp[i - 2] : 0;
                oddSum -= dp[i - 1];
            }
            evenSum += evenLength ? dp[nums.length - 1] : dp[nums.length - 2];
            oddSum += evenLength ? dp[nums.length - 2] : dp[nums.length - 1];
            if (evenSum == oddSum)
                res++;
        }

        return res;
    }

}
