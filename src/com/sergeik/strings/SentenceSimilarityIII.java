package com.sergeik.strings;

/**
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * For example, "Hello World", "HELLO", "hello world hello world" are all sentences. Words consist
 * of only uppercase and lowercase English letters.
 *
 * Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary sentence
 * (possibly empty) inside one of these sentences such that the two sentences become equal.
 * For example, sentence1 = "Hello my name is Jane" and sentence2 = "Hello Jane" can be made equal
 * by inserting "my name is" between "Hello" and "Jane" in sentence2.
 *
 * Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar.
 * Otherwise, return false.
 */
public class SentenceSimilarityIII {

    public static void main(String[] args) {
        assert !solutionOptimized("UI wqhw Lf", "ezzXqqEQcS");
        assert solutionOptimized("xD iP tqchblXgqvNVdi", "FmtdCzv Gp YZf UYJ xD iP tqchblXgqvNVdi");
        assert solutionOptimized("Ogn WtWj HneS", "Ogn WtWj HneS");
        assert !solutionOptimized("qbaVXO Msgr aEWD v ekcb", "Msgr aEWD ekcb");
        assert !solutionOptimized("B", "ByI BMyQIqce b bARkkMaABi vlR RLHhqjNzCN oXvyK zRXR q ff B yHS OD KkvJA P JdWksnH");
        assert !solutionOptimized("testte", "testbe");
        assert !solutionOptimized("of", "A lot of words");
        assert solutionOptimized("My name is Haley", "My Haley");
        assert solutionOptimized("Eating right now", "Eating");

        assert solution("xD iP tqchblXgqvNVdi", "FmtdCzv Gp YZf UYJ xD iP tqchblXgqvNVdi");
        assert solution("Ogn WtWj HneS", "Ogn WtWj HneS");
        assert !solution("qbaVXO Msgr aEWD v ekcb", "Msgr aEWD ekcb");
        assert !solution("B", "ByI BMyQIqce b bARkkMaABi vlR RLHhqjNzCN oXvyK zRXR q ff B yHS OD KkvJA P JdWksnH");
        assert !solution("testte", "testbe");
        assert !solution("of", "A lot of words");
        assert solution("My name is Haley", "My Haley");
        assert solution("Eating right now", "Eating");
    }

    private static boolean solutionOptimized(String sentence1, String sentence2) {

        String[] wordsShorter = sentence1.split(" ");
        String[] wordsLonger = sentence2.split(" ");
        if (wordsShorter.length > wordsLonger.length) {
            String[] tmp = wordsLonger;
            wordsLonger = wordsShorter;
            wordsShorter = tmp;
        }
        int i = 0;
        while (i < wordsShorter.length && wordsShorter[i].equals(wordsLonger[i]))
            i++;
        int diff = wordsLonger.length - wordsShorter.length;
        while (i < wordsShorter.length && wordsShorter[i].equals(wordsLonger[i + diff]))
            i++;
        return i == wordsShorter.length;
    }

    private static boolean solution(String sentence1, String sentence2) {

        String shorterStr = sentence1.length() > sentence2.length() ? sentence2 : sentence1;

        String[] sp1 = sentence1.split(" ");
        String[] sp2 = sentence2.split(" ");

        String[] shorter = sp1.length > sp2.length ? sp2 : sp1;
        String[] longer = shorter.equals(sp1) ? sp2 : sp1;

        StringBuilder prefixSb = new StringBuilder();
        StringBuilder suffixSb = new StringBuilder();

        int prefix = 0;
        while (prefix < shorter.length && sp1[prefix].equals(sp2[prefix])) {
            prefixSb.append(sp1[prefix]);
            prefixSb.append(" ");
            prefix++;
        }
        if (prefixSb.length() > 0)
            prefixSb.deleteCharAt(prefixSb.length() - 1);

        int pointer = shorter.length - 1;
        int diff = longer.length - shorter.length;
        while (pointer >= 0 && shorter[pointer].equals(longer[pointer + diff])) {
            suffixSb.insert(0, shorter[pointer]);
            suffixSb.insert(0, " ");
            pointer--;
        }
        if (suffixSb.length() > 0)
            suffixSb.deleteCharAt(0);

        if (prefixSb.toString().equals(shorterStr))
            return true;
        if (suffixSb.toString().equals(shorterStr))
            return true;
        if ((prefixSb.toString() + " " + suffixSb.toString()).equals(shorterStr))
            return true;
        return false;
    }

}
