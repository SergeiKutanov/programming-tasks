package com.sergeik.graphs;

import java.util.*;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around:
 * for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of
 * the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number
 * of turns required to open the lock, or -1 if it is impossible.
 */
public class OpenTheLock {

    public static void main(String[] args) {
        assert -1 == solution(new String[] {"0000"}, "8888");
        assert 1 == solution(new String[] {"8888"}, "0009");
        assert 6 == solution(new String[] {"0201", "0101", "0102", "1212", "2002"}, "0202");
    }

    private static int solution(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains(target) || dead.contains("0000"))
            return -1;

        Set<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add("0000");
        seen.add("0000");

        int level = 0;
        while (!queue.isEmpty()) {
            int size =queue.size();
            while (size > 0) {
                String node = queue.poll();
                if (target.equals(node))
                    return level;
                List<String> neigh = getNeighbours(node);
                for (String n: neigh) {
                    if (!seen.contains(n) && !dead.contains(n)) {
                        queue.add(n);
                        seen.add(n);
                    }
                }
                size--;
            }
            level++;
        }
        return -1;
    }

    private static List<String> getNeighbours(String str) {
        List<String> neighbours = new LinkedList<>();
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            char ch = sb.charAt(i);
            String s1 = sb.substring(0, i) + (ch == '9' ? '0' : (char)(ch + 1)) + sb.substring(i + 1);
            String s2 = sb.substring(0, i) + (ch == '0' ? '9' : (char)(ch - 1)) + sb.substring(i + 1);
            neighbours.add(s1);
            neighbours.add(s2);
        }
        return neighbours;
    }

}
