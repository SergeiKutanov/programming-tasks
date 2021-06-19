package com.sergeik.dynamic;


import java.util.*;

public class ConcatenatedWords {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"catdog"},
                solution(new String[] {"cat","dog","catdog"}).toArray()
        );

        assert Arrays.equals(
                new String[] {"ratcatdogcat","catsdogcats","dogcatsdog"},
                solution(new String[] {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"}).toArray()
        );
    }

    private static List<String> solution(String[] words) {
        if (words.length == 1)
            return new LinkedList<>();
        Set<String> dict = new HashSet<>();
        for (String w: words) {
            if (w.isEmpty())
                continue;
            dict.add(w);
        }


        Set<String> res = new HashSet<>();
        for (String w: words) {
            if (w.isEmpty())
                continue;
            dict.remove(w);
            dfs(w, dict, 0, res);
            dict.add(w);
        }
        List<String> ans = new LinkedList<>();
        for (String s: res)
            ans.add(s);
        return ans;
    }

    private static void dfs(String w, Set<String> dict, int start, Set<String> res) {
        if (start == w.length()) {
            res.add(w);
            return;
        }
        for (int i = start + 1; i <= w.length(); i++) {
            String str = w.substring(start, i);
            if (dict.contains(str))
                dfs(w, dict, i, res);
        }
    }

}
