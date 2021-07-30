package com.sergeik.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Two strings words[i] and words[j] are special-equivalent if after any number of moves, words[i] == words[j].
 *
 * For example, words[i] = "zzxy" and words[j] = "xyzz" are special-equivalent because we may make the moves
 * "zzxy" -> "xzzy" -> "xyzz".
 * A group of special-equivalent strings from words is a non-empty subset of words such that:
 *
 * Every pair of strings in the group are special equivalent, and
 * The group is the largest size possible (i.e., there is not a string words[i] not in the group such that words[i]
 * is special-equivalent to every string in the group).
 * Return the number of groups of special-equivalent strings from words.
 */
public class GroupsOfSpecialEquivalnetStrings {

    public static void main(String[] args) {
        assert 4 == solution(new String[]{"aa","bb","ab","ba"});
        assert 3 == solution(new String[] {"abc","acb","bac","bca","cab","cba"});
        assert 3 == solution(new String[] {"abcd","cdab","cbad","xyzz","zzxy","zzyx"});
    }

    private static int solution(String[] words) {
        Set<String> keys = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            int[] even = new int[26], odd = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                if (j % 2 == 0)
                    even[words[i].charAt(j) - 'a']++;
                else
                    odd[words[i].charAt(j) - 'a']++;
            }
            String key = Arrays.toString(even) + Arrays.toString(odd);
            keys.add(key);
        }
        return keys.size();
    }

    private static int arrSolution(String[] words) {
        int[][][] counts = new int[words.length][2][26];
        for (int k = 0; k < words.length; k++) {
            String w = words[k];
            int[] evenCount = new int[26];
            int[] oddCount = new int[26];
            for (int i = 0; i < w.length(); i++)
                if (i % 2 == 0)
                    evenCount[w.charAt(i) - 'a']++;
                else
                    oddCount[w.charAt(i) - 'a']++;
            counts[k][0] = evenCount;
            counts[k][1] = oddCount;
        }
        boolean[] used = new boolean[words.length];
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            if (used[i])
                continue;
            for (int j = i + 1; j < words.length; j++) {
                if (used[j]) continue;
                if (Arrays.equals(counts[i][0], counts[j][0]) && Arrays.equals(counts[i][1], counts[j][1]))
                    used[j] = true;
            }
            res++;
        }
        return res;
    }

    private static int oddEvenSolution(String[] words) {
        int[][][] counts = new int[words.length][2][26];
        for (int k = 0; k < words.length; k++) {
            String w = words[k];
            int[] evenCount = new int[26];
            int[] oddCount = new int[26];
            for (int i = 0; i < w.length(); i++)
                if (i % 2 == 0)
                    evenCount[w.charAt(i) - 'a']++;
                else
                    oddCount[w.charAt(i) - 'a']++;
            counts[k][0] = evenCount;
            counts[k][1] = oddCount;
        }
        boolean[] used = new boolean[words.length];
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            if (used[i])
                continue;
            for (int j = i + 1; j < words.length; j++) {
                if (used[j]) continue;
                if (Arrays.equals(counts[i][0], counts[j][0]) && Arrays.equals(counts[i][1], counts[j][1]))
                    used[j] = true;
            }
            res++;
        }
        return res;
    }

}
