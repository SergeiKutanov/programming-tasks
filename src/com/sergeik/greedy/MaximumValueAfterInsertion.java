package com.sergeik.greedy;

/**
 * You are given a very large integer n, represented as a string,​​​​​​ and an integer digit x.
 * The digits in n and the digit x are in the inclusive range [1, 9], and n may represent a negative number.
 *
 * You want to maximize n's numerical value by inserting x anywhere in the decimal representation of n​​​​​​.
 * You cannot insert x to the left of the negative sign.
 *
 * For example, if n = 73 and x = 6, it would be best to insert it between 7 and 3, making n = 763.
 * If n = -55 and x = 2, it would be best to insert it before the first 5, making n = -255.
 * Return a string representing the maximum value of n​​​​​​ after the insertion.
 */
public class MaximumValueAfterInsertion {

    public static void main(String[] args) {
        assert "4699757879438632651173569913153377".equals(solution("469975787943862651173569913153377", 3));
        assert "-1487261846914124419".equals(solution("-148726184691412441", 9));
        assert "-5648468153646".equals(solution("-648468153646", 5));
        assert "-1323".equals(solution("-132", 3));
        assert "763".equals(solution("73", 6));
        assert "999".equals(solution("99", 9));
        assert "-123".equals(solution("-13", 2));
    }

    private static String solution(String n, int x) {
        int start;
        StringBuilder sb = new StringBuilder();
        char xCh = (char) ('0' + x);
        if (n.charAt(0) == '-') {
            start = 1;
            sb.append(n.charAt(0));
            while (start < n.length() && n.charAt(start) <= xCh) {
                sb.append(n.charAt(start));
                start++;
            }
        } else {
            start = 0;
            while (start < n.length() && n.charAt(start) >= xCh) {
                sb.append(n.charAt(start));
                start++;
            }
        }
        sb.append(xCh);
        sb.append(n.substring(start));
        return sb.toString();
    }

}
