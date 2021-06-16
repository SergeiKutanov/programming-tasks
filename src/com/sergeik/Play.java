package com.sergeik;

import java.util.LinkedList;
import java.util.List;

public class Play {


    public static void main(String[] args) {
        List<String> res = solution(3);
        for (String s: res)
            System.out.println(s);
    }

    public static List<String> solution(int n) {
        List<String> res = new LinkedList<>();
        dfs(n, 0, 0, res, new StringBuilder());
        return res;
    }

    private static void dfs(int n, int left, int right, List<String> res, StringBuilder sb) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }

        if (left < n) {
            sb.append('(');
            dfs(n, left + 1, right, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left) {
            sb.append(')');
            dfs(n, left, right + 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
