package com.sergeik.strings;


import java.util.*;

/**
 * A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com",
 * at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com".
 * When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com"
 * and "com" implicitly.
 *
 * Now, call a "count-paired domain" to be a count (representing the number of visits this domain received),
 * followed by a space, followed by the address. An example of a count-paired domain might
 * be "9001 discuss.leetcode.com".
 *
 * We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains,
 * (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
 */
public class SubdomainVisitCount {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"951 com", "900 google.mail.com", "1 intel.mail.com", "5 org", "5 wiki.org", "901 mail.com", "50 yahoo.com"},
                solution(new String[] {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}).toArray()
            );
    }

    private static List<String> solution(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String str: cpdomains) {
            int count = Integer.parseInt(str.substring(0, str.indexOf(" ")));
            String key = str.substring(str.indexOf(" ") + 1);
            int idx = 0;
            map.put(key, map.getOrDefault(key, 0) + count);
            while ((idx = str.indexOf('.', idx)) != -1) {
                idx++;
                key = str.substring(idx);
                map.put(key, map.getOrDefault(key, 0) + count);
            }
        }
        List<String> res = new LinkedList<>();
        for (String key: map.keySet())
            res.add(map.get(key) + " " + key);
        return res;
    }

}
