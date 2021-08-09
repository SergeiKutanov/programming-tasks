package com.sergeik.strings;

import java.util.*;

/**
 * Given an array of string words. Return all strings in words which is substring of another word in any order.
 *
 * String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right
 * side of words[j].
 */
public class StringMatchingInAnArray {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {
                        "fvbif","tfvbifde","gmhsiffallywmr","tfwihdq","bfvbifh","gfda","upmibm","adsr","f",
                        "tfvbifdehx","kmtfvbifdehxo","q","npvlrbikqibdwol"},
                solution(new String[] {
                        "fvbif","tfvbifde","xrzb","ltfvbifde","gmhsiffallywmr","nqgmhsiffallywmrq","tfwihdq",
                        "mtfvbifdee","jxhlyhl","bfvbifh","gfda","hgfdahc","upmibm","syupmibmv","adsr","arfvbifkg",
                        "f","tfvbifdehx","qvddxaw","kmtfvbifdehxo","nbrwfdvlwmyu","sggfda","kphirqxepnezk","bkfvbifyj",
                        "q","vgbfvbifhzl","rcnmsbpblgwwv","rkmtfvbifdehxo","npvlrbikqibdwol","rtfwihdq","urjcaqlyq",
                        "zrbfvbifhu","naduhc","lnpvlrbikqibdwol","gdblfkgxurcuk","adsrnx"}).toArray()
        );
        assert Arrays.equals(
                new String[] {"as", "hero"},
                solution(new String[] {"mass", "as", "hero", "superhero"}).toArray()
        );
    }

    private static List<String> hashSetSolution(String[] words) {
        Set<String> res = new HashSet<>();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (i == j) continue;
                if (words[i].contains(words[j]))
                    res.add(words[j]);
                if (words[j].contains(words[i]))
                    res.add(words[i]);
            }
        }
        return new ArrayList<>(res);
    }

    private static List<String> solution(String[] words) {
        boolean[] mask = new boolean[101];
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (i == j) continue;
                if (mask[i] || mask[j]) continue;
                if (words[i].contains(words[j]))
                    mask[j] = true;
                if (words[j].contains(words[i]))
                    mask[i] = true;
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (mask[i])
                res.add(words[i]);
        }
        return res;
    }

}
