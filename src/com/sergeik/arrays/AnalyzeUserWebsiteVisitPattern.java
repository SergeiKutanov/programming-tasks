package com.sergeik.arrays;

import java.util.*;

/**
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 *
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.
 * (The websites in a 3-sequence are not necessarily distinct.)
 *
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution, return
 * the lexicographically smallest such 3-sequence.
 */
public class AnalyzeUserWebsiteVisitPattern {

    public static void main(String[] args) {

        assert Arrays.equals(
                new String[] {"hibympufi","hibympufi","yljmntrclw"},
                solution(
                        new String[] {"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"},
                        new int[] {527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930},
                        new String[] {"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"}
                ).toArray()
        );

        assert Arrays.equals(
                new String[] {"home", "about", "career"},
                solution(
                        new String[] {"joe","joe","joe","james","james","james","james","mary","mary","mary"},
                        new int[] {1,2,3,4,5,6,7,8,9,10},
                        new String[] {"home","about","career","home","cart","maps","home","home","about","career"}
                ).toArray()
        );

    }

    private static List<String> solution(String[] username, int[] timestamp, String[] website) {
        Map<String, Integer> freq = new HashMap<>();
        Map<String, TreeMap<Integer, String>> userWebsiteMap = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            TreeMap<Integer, String> treeMap = userWebsiteMap.getOrDefault(username[i], new TreeMap<>());
            treeMap.put(timestamp[i], website[i]);
            userWebsiteMap.put(username[i], treeMap);
        }

        for (String user: userWebsiteMap.keySet()) {
            TreeMap<Integer, String> websitesVisited = userWebsiteMap.get(user);
            buildSeq(websitesVisited, freq);
        }

        String res = "";
        int max = 0;
        for (String key: freq.keySet()) {
            int cMax = freq.get(key);
            if (cMax > max) {
                max = cMax;
                res = key;
            } else if (cMax == max) {
                if (key.compareTo(res) < 0)
                    res = key;
            }
        }

        String[] seq = res.split("#");
        List<String> ans = new LinkedList<>(Arrays.asList(seq));
        return ans;
    }

    private static void buildSeq(TreeMap<Integer, String> websitesVisited, Map<String, Integer> freq) {
        Object[] websites = websitesVisited.values().toArray();
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < websites.length - 2; i++)
            for (int j = i + 1; j < websites.length - 1; j++)
                for (int k = j + 1; k < websites.length; k++) {
                    String key = websites[i] + "#" + websites[j] + "#" + websites[k];
                    if (seen.contains(key))
                        continue;
                    seen.add(key);
                    freq.put(key, freq.getOrDefault(key, 0) + 1);
                }
    }


}
