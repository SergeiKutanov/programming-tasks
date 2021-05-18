package com.sergeik.hashtable;

import java.util.*;

public class FindDuplicateFileInSystem {

    public static void main(String[] args) {
        List<List<String>> exp = new LinkedList<>();
        exp.add(Arrays.asList("root/a/2.txt","root/c/d/4.txt","root/4.txt"));
        exp.add(Arrays.asList("root/a/1.txt","root/c/3.txt"));
        assert validate(
                exp,
                solution(new String[]{
                        "root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"
                })
        );

        exp = new LinkedList<>();
        exp.add(Arrays.asList("root/a/2.txt","root/c/d/4.txt"));
        exp.add(Arrays.asList("root/a/1.txt","root/c/3.txt"));
        assert validate(
                exp,
                solution(new String[]{
                        "root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"
                })
        );
    }

    private static boolean validate(List<List<String>> l1, List<List<String>> l2) {
        if (l1.size() != l2.size())
            return false;
        for (int i = 0; i < l1.size(); i++) {
            List<String> ll1 = l1.get(i);
            List<String> ll2 = l2.get(i);
            if (ll1.size() != ll2.size())
                return false;
            for (int j = 0; j < ll1.size(); j++) {
                if (!ll1.get(j).equals(ll2.get(j)))
                    return false;
            }
        }
        return true;
    }

    private static List<List<String>> solution(String[] paths) {
        Map<String, List<String>> res = new HashMap<>();
        for (String path: paths) {
            String[] parts = path.split("\\s");
            for (int i = 1; i < parts.length; i++) {
                int ind = parts[i].indexOf('(');
                String fileName = parts[i].substring(0, ind);
                String contents = parts[i].substring(ind + 1, parts[i].length() - 1);
                if (!res.containsKey(contents))
                    res.put(contents, new LinkedList<>());
                res.get(contents).add(parts[0] + "/" + fileName);
            }
        }
        List<List<String>> ret = new LinkedList<>();
        for (String key: res.keySet()) {
            if (res.get(key).size() > 1)
                ret.add(res.get(key));
        }
        return ret;
    }

}
