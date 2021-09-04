package com.sergeik.backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FindUniqueBinaryString {

    public static void main(String[] args) {

        assert "11".equals(solution(new String[] {"01", "10"}));
        assert "000".equals(solution(new String[] {"111", "011", "001"}));

        assert "00".equals(setSolution(new String[] {"01", "10"}));
        assert "000".equals(setSolution(new String[] {"111", "011", "001"}));
    }

    private static String solution(String[] nums) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            res.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }

        return res.toString();
    }

    private static String setSolution(String[] nums) {
        int n = nums.length;
        boolean[] ints = new boolean[1 << n];
        for (String str: nums) {
            ints[Integer.parseInt(str, 2)] = true;
        }
        for (int i = 0; i < 1 << n; i++) {
            if (!ints[i]) {
                StringBuilder res = new StringBuilder(Integer.toBinaryString(i));
                while (res.length() < n)
                    res.insert(0, "0");
                return res.toString();
            }
        }
        return "";
    }

}
