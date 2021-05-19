package com.sergeik.arrays;

import java.util.HashSet;
import java.util.Set;

public class TotalFruit {

    public static void main(String[] args) {
        assert 3 == solution(new int[]{1,2,1});
        assert 5 == solution(new int[]{3,3,3,1,2,1,1,2,3,3,4});
    }

    private static int solution(int[] tree) {
        if (tree.length < 3)
            return tree.length;
        int start = 0;
        int end = 1;
        Set<Integer> seen = new HashSet<>();
        seen.add(tree[start]);
        seen.add(tree[end]);
        int max = end - start + 1;
        while (end < tree.length && start < end) {
            if (seen.contains(tree[end])) {
                end++;
            } else {
                if (seen.size() < 2) {
                    seen.add(tree[end]);
                    end++;
                } else {
                    max = Math.max(max, end - start);
                    start++;
                    end = start + 1;
                    seen.clear();
                    seen.add(tree[start]);
                }
            }
        }
        max = Math.max(max, end - start);
        return max;
    }

}
