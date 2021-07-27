package com.sergeik.strings;

public class MaximumScoreWordsFormedByLetters {

    private static int res = 0;

    public static void main(String[] args) {

        assert 82 == solution(
                new String[] {"aadc","daeaeeb","abebce","dddcb","bccdbdab","adadb","adca"},
                new char[] {'a','a','a','a','a','a','a','a','a','b','b','b','b','b','b','b','b','b','c','c','c','c',
                            'c','c','c','d','d','d','e','e','e','e','e','e','e','e','e'},
                new int[] {6,3,2,5,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        );

        assert 23 == solution(
                new String[] {"dog","cat","dad","good"},
                new char[] {'a','a','c','d','d','d','g','o','o'},
                new int[] {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0}
        );
    }


    private static int solution(String[] words, char[] letters, int[] score) {
        int[] let = new int[26];
        for (char ch: letters)
            let[ch - 'a']++;
        return dfs(words, let, score, 0);
    }

    private static int dfs(String[] words, int[] letters, int[] score, int idx) {
        int max = 0;
        for (int i = idx; i < words.length; i++) {
            int res = 0;
            boolean isValid = true;
            for (char ch: words[i].toCharArray()) {
                letters[ch - 'a']--;
                res += score[ch - 'a'];
                if (letters[ch - 'a'] < 0)
                    isValid = false;
            }
            if (isValid) {
                res += dfs(words, letters, score, i + 1);
                max = Math.max(res, max);
            }
            for (char ch: words[i].toCharArray()) {
                letters[ch - 'a']++;
            }
        }
        return max;
    }

    private static void dfs(String[] words, int[] letters, int[] score, int idx, int cScore) {
        if (idx == words.length) {
            res = Math.max(res, cScore);
            return;
        }
        for (int i = idx; i < words.length; i++) {
            String w = words[i];
            int wordScore = 0;
            boolean build = false;

            int[] used = new int[26];
            for (int j = 0; j < w.length(); j++) {
                int charId = w.charAt(j) - 'a';
                if (letters[charId] <= 0)
                    break;
                wordScore += score[charId];
                letters[charId]--;
                used[charId]++;
                if (j == w.length() - 1)
                    build = true;
            }

            if (!build) {
                for (int ch = 0; ch < used.length; ch++)
                    letters[ch] += used[ch];
                wordScore = 0;
            }

            dfs(words, letters, score, i + 1, cScore + wordScore);

            if (build) {
                for (int ch = 0; ch < used.length; ch++)
                    letters[ch] += used[ch];
            }


        }
    }

}
