package com.sergeik.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 */
public class FractionToRecurringDecimal {

    public static void main(String[] args) {
        assert "0.0000000004656612873077392578125".equals(solution(-1, -2147483648));
        assert "0.1(6)".equals(solution(1, 6));
        assert "0.(012)".equals(solution(4, 333));
        assert "0.(6)".equals(solution(2, 3));
    }

    private static String solution(int numerator, int denominator) {

        if (numerator == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        sb.append(
                (numerator < 0 && denominator > 0) ^ (numerator > 0 && denominator < 0) ? "-" : ""
        );
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        sb.append(num / den);
        num %= den;
        if (num == 0)
            return sb.toString();

        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        map.put(num, sb.length());
        while (num != 0) {
            num *= 10;
            sb.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                sb.insert(index , "(");
                sb.append(")");
                break;
            } else {
                map.put(num, sb.length());
            }
        }
        return sb.toString();
    }

}
