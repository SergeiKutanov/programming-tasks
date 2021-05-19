package com.sergeik.recursion;

/**
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and
 * replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 *
 * Given row n and index k, return the kth indexed symbol in row n. (The values of k are 1-indexed.) (1 indexed).
 */
public class KthSymbolInGrammar {

    public static void main(String[] args) {
        assert 1 == solution(4, 5);
    }

    private static int solution(int n, int k) {
        if (n == 1)
            return 0;
        int parent = solution(n - 1, k / 2 + k % 2);
        boolean isOdd = k % 2 == 1;
        if (parent == 1) //parent 1, child == 10, if original k is odd then it's left child of parent
            return isOdd ? 1 : 0;
        return isOdd ? 0 : 1;
    }
}