package com.sergeik.greedy;

/**
 * You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to
 * make these two strings equal to each other. You can swap any two characters that belong to different strings,
 * which means: swap s1[i] and s2[j].
 *
 * Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.
 */
public class MinimumSwapsToMakeStringsEquals {

    public static void main(String[] args) {
        assert 1 == solution("xx", "yy");
        assert 2 == solution("xy", "yx");
        assert -1 == solution("xx", "xy");
        assert 4 == solution("xxyyxyxyxx", "xyyxyxxxyx");
    }

    /**
     * xx - yy = 1 swap
     * xy - yx = 2 swap
     * xx - yx = -1 impossible
     * @param s1
     * @param s2
     * @return
     */
    private static int solution(String s1, String s2) {
        int xy = 0, yx = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.charAt(i) == 'x') {
                    xy++;
                } else {
                    yx++;
                }
            }
        }
        if ((xy + yx) % 2 != 0)
            return -1;
        int res = xy / 2 + xy % 2;
        res += yx / 2 + yx % 2;
        return res;
    }

}
