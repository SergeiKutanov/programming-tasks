package com.sergeik.hashtable;

import com.sergeik.linkedlists.ListHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        assert ListHelper.compareIntLists(
                Arrays.asList(5,6),
                solution(new int[] {4,3,2,7,8,2,3,1})
        );
    }

    private static List<Integer> solution(int[] nums) {
        boolean[] set = new boolean[nums.length];
        for (int n: nums)
            set[n - 1] = true;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < set.length; i++)
            if (!set[i])
                res.add(i + 1);
        return res;
    }

}
