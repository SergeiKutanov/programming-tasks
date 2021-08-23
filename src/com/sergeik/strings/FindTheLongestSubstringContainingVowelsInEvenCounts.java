package com.sergeik.strings;

import java.util.HashMap;

/**
 * Given the string s, return the size of the longest substring containing each vowel an even number of times.
 * That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 */
public class FindTheLongestSubstringContainingVowelsInEvenCounts {

    public static void main(String[] args) {
        assert 6 == solution("bcbcbc");
        assert 5 == solution("leetcodeisgreat");
        assert 13 == solution("eleetminicoworoep");
    }

    private static int solution(String s) {
        int res = 0 , cur = 0, n = s.length();
        Integer[] seen = new Integer[1 << 5];
        seen[0] = -1;
        for (int i = 0; i < n; i++) {
            cur ^= 1 << ("aeiou".indexOf(s.charAt(i)) + 1 ) >> 1;
            if (seen[cur] == null)
                seen[cur] = i;
            res = Math.max(res, i - seen[cur]);
        }
        return res;
    }

    private static int mapSolution(String s) {
        int res = 0 , cur = 0, n = s.length();
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; i++) {
            cur ^= 1 << ("aeiou".indexOf(s.charAt(i)) + 1 ) >> 1;
            seen.putIfAbsent(cur, i);
            res = Math.max(res, i - seen.get(cur));
        }
        return res;
    }

}
