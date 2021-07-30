package com.sergeik.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaximumLnegthOfAConcatenatedStringWithUniqueCharacters {

    private static int res = 0;

    public static void main(String[] args) {
        assert 16 == solution(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"));
        assert 26 == solution(Arrays.asList("abcdefghijklmnopqrstuvwxyz"));
        assert 6 == solution(Arrays.asList("cha","r","act","ers"));
        assert 4 == solution(Arrays.asList("un", "iq", "ue"));
    }

    private static int solution(List<String> arr) {
        res = 0;
        dfs(arr, "", 0);
        return res;
    }

    private static void dfs(List<String> arr, String path, int idx) {
        boolean isUniqueChar = isUniqueChars(path);

        if (isUniqueChar) {
            res = Math.max(path.length(), res);
        }

        if (idx == arr.size() || !isUniqueChar) {
            return;
        }

        for (int i = idx; i < arr.size(); i++) {
            dfs(arr, path + arr.get(i), i + 1);
        }
    }

    private static boolean isUniqueChars(String s) {
        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }

}
