package com.sergeik.strings;

import java.util.Arrays;

/**
 * Given a string s consisting only of characters a, b and c.
 *
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 */
public class NumberOfSubstringsContainingAllThreeCharacters {

    public static void main(String[] args) {
        assert 1 == solution("abc");
        assert 3 == solution("aaacb");
        assert 10 == solution("abcabc");
    }

    private static int solution(String s) {
        int n = s.length();
        int[] a = new int[n], b = new int[n], c = new int[n];
        Arrays.fill(a, -1);
        Arrays.fill(b, -1);
        Arrays.fill(c, -1);
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (i < s.length() - 1) {
                a[i] = a[i + 1];
                b[i] = b[i + 1];
                c[i] = c[i + 1];
            }
            if (ch == 'a')
                a[i] = i;
            else if (ch == 'b')
                b[i] = i;
            else if (ch == 'c')
                c[i] = i;
        }
        int res = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            if (a[i] == -1 || b[i] == -1 || c[i] == -1)
                continue;
            int max = Math.max(a[i], b[i]);
            max = Math.max(max, c[i]);
            res += n - max;
        }
        return res;
    }

    private static int bruteSolution(String s) {
        int n = s.length();
        int[] a = new int[n + 1], b = new int[n + 1], c = new int[n + 1];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            a[i + 1] = a[i];
            b[i + 1] = b[i];
            c[i + 1] = c[i];
            if (ch == 'a')
                a[i + 1]++;
            else if (ch == 'b')
                b[i + 1]++;
            else if (ch == 'c')
                c[i + 1]++;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                boolean hasA = a[j] - a[i] > 0;
                boolean hasB = b[j] - b[i] > 0;
                boolean hasC = c[j] - c[i] > 0;
                if (hasA && hasB && hasC)
                    res++;
            }
        }
        return res;
    }

}
