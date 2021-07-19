package com.sergeik.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given the array queries of positive integers between 1 and m, you have to process all queries[i]
 * (from i=0 to i=queries.length-1) according to the following rules:
 *
 * In the beginning, you have the permutation P=[1,2,3,...,m].
 * For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at
 * the beginning of the permutation P. Notice that the position of queries[i] in P is the result for queries[i].
 * Return an array containing the result for the given queries.
 */
public class QueriesOnAPermutationWithKey {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {2,1,2,1}, solution(new int[]{3,1,2,1}, 5));
    }

    private static int[] solution(int[] queries, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= m; i++)
            list.add(i);

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i];
            int idx = 0;
            Iterator<Integer> iterator = list.iterator();
            while (iterator.next() != val)
                idx++;
            res[i] = idx;
            list.remove(idx);
            list.add(0, val);
        }

        return res;
    }

}
