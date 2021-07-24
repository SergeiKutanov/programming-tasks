package com.sergeik.trees;

/**
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 *
 * You have to form a team of 3 soldiers amongst them under the following rules:
 *
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k])
 * where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 */
public class CountNumberOfTeams {

    public static void main(String[] args) {
        assert 3 == solution(new int[] {2,5,3,4,1});
    }

    private static int solution(int[] rating) {
        if (rating.length < 3)
            return 0;
        int[] leftTree = new int[(int) 1e5 + 1];
        int[] rightTree = new int[(int) 1e5 + 1];

        for (int r: rating)
            update(rightTree, r, 1);

        int res = 0;
        for (int r: rating) {
            update(rightTree, r, -1);
            res += getPrefixSum(leftTree, r - 1) * getSuffixSum(rightTree, r + 1);
            res += getSuffixSum(leftTree, r + 1) * getPrefixSum(rightTree, r - 1);
            update(leftTree, r, 1);
        }
        return res;
    }

    private static void update(int[] bit, int idx, int val) {
        while (idx < bit.length) {
            bit[idx] += val;
            idx += idx & (-idx);
        }
    }

    private static int getPrefixSum(int[] bit, int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

    private static int getSuffixSum(int[] bit, int idx) {
        return getPrefixSum(bit, (int) 1e5) - getPrefixSum(bit, idx - 1);
    }

}
