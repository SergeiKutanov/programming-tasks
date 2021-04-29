package com.sergeik.math;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {

    public static void main(String[] args) {

        assert 3 == solutionWithChars("III");
        assert 4 == solutionWithChars("IV");
        assert 9 == solutionWithChars("IX");
        assert 58 == solutionWithChars("LVIII");
        assert 1994 == solutionWithChars("MCMXCIV");

        assert 3 == solution("III");
        assert 4 == solution("IV");
        assert 9 == solution("IX");
        assert 58 == solution("LVIII");
        assert 1994 == solution("MCMXCIV");

        assert 3 == prevTrackSolution("III");
        assert 4 == prevTrackSolution("IV");
        assert 9 == prevTrackSolution("IX");
        assert 58 == prevTrackSolution("LVIII");
        assert 1994 == prevTrackSolution("MCMXCIV");

    }

    private static int solutionWithChars(String s) {
        int val = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (i > 0
                    && (
                            (s.charAt(i-1) == 'I' && (s.charAt(i) == 'V' || s.charAt(i) == 'X'))
                            || (s.charAt(i-1) == 'X' && (s.charAt(i) == 'L' || s.charAt(i) == 'C'))
                            || (s.charAt(i-1) == 'C' && (s.charAt(i) == 'D' || s.charAt(i) == 'M'))
                    )
            ) {
                val -= value(s.charAt(i-1));
                i--;
            }
            val += value(ch);
        }
        return val;
    }

    private static int prevTrackSolution(String s) {

        char prev = s.charAt(s.length() - 1);
        int val = value(prev);
        for (int i = s.length() - 2; i >= 0; i--) {
            char ch = s.charAt(i);
            if (
                    (ch == 'I' && (prev == 'V' || prev == 'X'))
                    || (ch == 'X' && (prev == 'L' || prev == 'C'))
                    || (ch == 'C' && (prev == 'D' || prev == 'M'))
            ) {
                val -= value(ch);
            } else {
                val += value(ch);
            }
            prev = ch;
        }
        return val;
    }

    static int value(char r)
    {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

    private static int solution(String s) {
        Map<String, Integer> valuesMap = new HashMap<>();
        valuesMap.put("I", 1);
        valuesMap.put("V", 5);
        valuesMap.put("X", 10);
        valuesMap.put("L", 50);
        valuesMap.put("C", 100);
        valuesMap.put("D", 500);
        valuesMap.put("M", 1000);
        valuesMap.put("IV", 4);
        valuesMap.put("IX", 9);
        valuesMap.put("XL", 40);
        valuesMap.put("XC", 90);
        valuesMap.put("CD", 400);
        valuesMap.put("CM", 900);

        int val = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            String romanVal = s.substring(i, i+1);
            if (i > 0) {
                String pref = s.substring(i-1, i);
                if (valuesMap.containsKey(pref + romanVal)) {
                    romanVal = pref + romanVal;
                    i--;
                }
            }
            val += valuesMap.get(romanVal);
        }
        return val;
    }

}
