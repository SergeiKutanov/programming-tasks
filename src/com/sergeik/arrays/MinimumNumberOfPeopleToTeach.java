package com.sergeik.arrays;

import java.util.*;

/**
 * On a social network consisting of m users and some friendships between users, two users can communicate
 * with each other if they know a common language.
 *
 * You are given an integer n, an array languages, and an array friendships where:
 *
 * There are n languages numbered 1 through n,
 * languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
 * friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
 * You can choose one language and teach it to some users so that all friends can communicate with each other.
 * Return the minimum number of users you need to teach.
 *
 * Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't
 * guarantee that x is a friend of z.
 */
public class MinimumNumberOfPeopleToTeach {

    public static void main(String[] args) {
        assert 0 == solution(2, new int[][] {
                {2,1},
                {1,3},
                {1,2},
                {3,1}
        }, new int[][] {
                {1,4},
                {1,2},
                {3,4},
                {2,3}
        });

        assert 2 == solution(2, new int[][] {
                {2},
                {1,3},
                {1,2},
                {3}
        }, new int[][] {
                {1,4},
                {1,2},
                {3,4},
                {2,3}
        });
    }

    private static int solution(int n, int[][] languages, int[][] friendships) {
        Map<Integer, Set<Integer>> langMap = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            Set<Integer> langs = new HashSet<>();
            for (int l: languages[i])
                langs.add(l);
            langMap.put(i + 1, langs);
        }
        List<int[]> toLearn = new LinkedList<>();
        Set<Integer> lang = new HashSet<>();
        for (int[] fr: friendships) {
            if (!canCommunicate(langMap.get(fr[0]), langMap.get(fr[1]))) {
                toLearn.add(fr);
                lang.addAll(langMap.get(fr[0]));
                lang.addAll(langMap.get(fr[1]));
            }
        }

        if (lang.size() == 0)
            return 0;

        int min = Integer.MAX_VALUE;
        for (int i: lang) {
            min = Math.min(min, tryLanguage(i, toLearn, langMap));
        }
        return min;
    }

    private static boolean canCommunicate(Set<Integer> s1, Set<Integer> s2) {
        for (int s: s1)
            if (s2.contains(s))
                return true;
        return false;
    }


    private static int tryLanguage(int lang, List<int[]> friendships, Map<Integer, Set<Integer>> languages) {
        Set<Integer> learnt = new HashSet<>();
        for (int[] fr: friendships) {
            Set<Integer> lang1 = languages.get(fr[0]);
            Set<Integer> lang2 = languages.get(fr[1]);
            if (!lang1.contains(lang))
                learnt.add(fr[0]);
            if (!lang2.contains(lang))
                learnt.add(fr[1]);
        }
        return learnt.size();
    }

}
