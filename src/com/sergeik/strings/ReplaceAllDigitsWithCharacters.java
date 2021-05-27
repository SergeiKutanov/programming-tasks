package com.sergeik.strings;

/**
 * You are given a 0-indexed string s that has lowercase English letters in its even indices
 * and digits in its odd indices.
 *
 * There is a function shift(c, x), where c is a character and x is a digit, that returns the xth character after c.
 *
 * For example, shift('a', 5) = 'f' and shift('x', 0) = 'x'.
 * For every odd index i, you want to replace the digit s[i] with shift(s[i-1], s[i]).
 *
 * Return s after replacing all digits. It is guaranteed that shift(s[i-1], s[i]) will never exceed 'z'.
 */
public class ReplaceAllDigitsWithCharacters {

    public static void main(String[] args) {
        /*
        The digits are replaced as follows:
            - s[1] -> shift('a',1) = 'b'
            - s[3] -> shift('b',2) = 'd'
            - s[5] -> shift('c',3) = 'f'
            - s[7] -> shift('d',4) = 'h'

         */
        assert "abbdcfdhe".equals(solution("a1b2c3d4e"));
    }

    private static String solution(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0)
                sb.append(s.charAt(i));
            else {
                char ch = (char)(s.charAt(i - 1) + s.charAt(i) - '0');
                sb.append(ch);
            }
        }
        return sb.toString();
    }

}
