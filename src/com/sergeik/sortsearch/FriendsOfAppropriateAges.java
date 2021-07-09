package com.sergeik.sortsearch;

import java.util.HashMap;
import java.util.Map;

/**
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 *
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 *
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 *
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 *
 * How many total friend requests are made?
 */
public class FriendsOfAppropriateAges {

    public static void main(String[] args) {
        assert 29 == solution(new int[] {73,106,39,6,26,15,30,100,71,35,46,112,6,60,110});
        assert 4 == solution(new int[] {30,48,56,69,101});
        assert 2 == solution(new int[] {16,16});
        assert 2 == solution(new int[] {16,17,18});
        assert 3 == solution(new int[] {20,30,100,110,120});
    }

    private static int solution(int[] ages) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n: ages)
            count.put(n, count.getOrDefault(n, 0) + 1);
        int res = 0;
        for (int i: count.keySet()) {
            for (int j: count.keySet()) {
                if (request(i, j)) {
                    res += count.get(i) * (count.get(j) - (i == j ? 1 : 0));
                }
            }
        }
        return res;
    }

    private static int arraySolution(int[] ages) {
        int[] count = new int[121];
        for (int n: ages)
            count[n]++;
        int res = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count.length; j++) {
                if (count[i] == 0 || count[j] == 0)
                    continue;
                if (request(i, j)) {
                   res += count[i] * (count[j] - (i == j ? 1 : 0));
                }
            }
        }
        return res;
    }

    private static boolean request(int a, int b) {
        if (b <= a * 0.5 + 7)
            return false;
        if (b > a)
            return false;
        return true;
    }

}
