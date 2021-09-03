package com.sergeik.dynamic;

public class MinimumDistanceToTypeAWordUsingTwoFingers {

    public static void main(String[] args) {
        assert 3 == solution("CAKE");
        assert 6 == solution("HAPPY");
    }

    static int[][][] memo = new int[27][27][300];

    public static int solution(String word) {
        memo = new int[27][27][300];
        return minDist(word, 0, null, null);
    }

    private static int minDist(String word, int pos, Character c1, Character c2) {
        if (pos >= word.length())
            return 0;
        int idx1 = c1 == null ? 0 : c1 - 'A' + 1;
        int idx2 = c2 == null ? 0 : c2 - 'A' + 1;
        if (memo[idx1][idx2][pos] == 0) {
            memo[idx1][idx2][pos] = Math.min(
                    getDist(c1,word.charAt(pos)) + minDist(word,pos+1,word.charAt(pos),c2),
                    getDist(c2,word.charAt(pos)) + minDist(word,pos+1,c1,word.charAt(pos))
            );
        }
        return memo[idx1][idx2][pos];
    }

    private static int getDist(Character c1, Character c2) {
        if (c1 == null || c2 == null) return 0;
        int d1 = c1 - 'A', d2 = c2 - 'A';
        int x1 = d1 / 6, y1 = d1 % 6;
        int x2 = d2 / 6, y2 = d2 % 6;
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    public int minimumDistance(String word) {
        int dp[] = new int[26], res = 0, save = 0, n = word.length();
        for (int i = 0; i < n - 1; ++i) {
            int b = word.charAt(i) - 'A', c = word.charAt(i + 1) - 'A';
            for (int a = 0; a < 26; ++a)
                dp[b] = Math.max(dp[b], dp[a] + d(b, c) - d(a, c));
            save = Math.max(save, dp[b]);
            res += d(b, c);
        }
        return res - save;

    }

    private int d(int a, int b) {
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
}
