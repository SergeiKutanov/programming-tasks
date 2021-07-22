package com.sergeik.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You have n  tiles, where each tile has one letter tiles[i] printed on it.
 *
 * Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.
 */
public class LetterTilesPossibilities {

    private static int res = 0;

    public static void main(String[] args) {
        assert 8 == hashMapSolution("AAB");
        assert 188 == hashMapSolution("AAABBC");
        assert 188 == solution("AAABBC");
        assert 8 == solution("AAB");
    }

    private static int hashMapSolution(String tiles) {
        Set<String> words = new HashSet<>();
        StringBuilder word = new StringBuilder();
        boolean[] used = new boolean[tiles.length()];
        dfS(words, word, used, tiles);
        return words.size();
    }

    private static void dfS(Set<String> words, StringBuilder word, boolean[] used, String tiles) {
        String w = word.toString();
        if (!w.isEmpty() && !words.contains(w))
            words.add(w);
        if (w.length() == tiles.length())
            return;
        for (int i = 0; i < tiles.length(); i++) {
            if (used[i])
                continue;
            word.append(tiles.charAt(i));
            used[i] = true;
            dfS(words, word, used, tiles);
            used[i] = false;
            word.setLength(word.length() - 1);
        }
    }

    private static int solution(String tiles) {
        res = 0;
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        backtrack(chars, new boolean[chars.length]);
        return res;
    }

    private static void backtrack(char[] chars, boolean[] used) {
        for (int i = 0; i < chars.length; i++) {
            if (used[i])
                continue;
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])
                continue;

            res++;
            used[i] = true;
            backtrack(chars, used);
            used[i] = false;
        }
    }

}
