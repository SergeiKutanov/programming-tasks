package com.sergeik.hashtable;

import java.util.*;

/**
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list,
 * so that the concatenation of the two words words[i] + words[j] is a palindrome.
 */
public class PalindromePairs {

    public static void main(String[] args) {
        int[][] exp = new int[][] {{0,1},{1,0},{3,2},{2,4}};
        List<List<Integer>> res = solution(new String[] {"abcd", "dcba", "lls", "s", "sssll"});
        for (int i = 0; i < exp.length; i++)
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res.get(i).get(j);
    }

    /**
     * Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.
     *
     * Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.
     *
     * Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.
     *
     * Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.
     * @param words
     * @return
     */
    private static List<List<Integer>> solution(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        List<List<Integer>> res = new LinkedList<>();
        //case 1
        if (map.containsKey("")) {
            int idx = map.get("");
            for (int i = 0; i < words.length; i++) {
                if (isPalindrome(words[i])) {
                    if (map.get(words[i]) == idx)
                        continue;
                    res.add(Arrays.asList(idx, i));
                    res.add(Arrays.asList(i, idx));
                }
            }
        }

        //case 2
        for (int i = 0; i < words.length; i++) {
            String rev = new StringBuilder(words[i]).reverse().toString();
            if (map.containsKey(rev) && map.get(rev) != i) {
                res.add(Arrays.asList(i, map.get(rev)));
            }
        }

        //cases 3 and 4
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            for (int cut = 1; cut < w.length(); cut++) {
                if (isPalindrome(w.substring(0, cut))) {
                    String rev = new StringBuilder(w.substring(cut)).reverse().toString();
                    if (map.containsKey(rev) && map.get(rev) != i)
                        res.add(Arrays.asList(map.get(rev), i));
                }
                if (isPalindrome(w.substring(cut))) {
                    String rev = new StringBuilder(w.substring(0, cut)).reverse().toString();
                    if (map.containsKey(rev) && map.get(rev) != i)
                        res.add(Arrays.asList(i, map.get(rev)));
                }
            }
        }

        return res;
    }

    private static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else
                return false;
        }
        return true;
    }

}
