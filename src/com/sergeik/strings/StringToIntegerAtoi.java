package com.sergeik.strings;

public class StringToIntegerAtoi {

    public static void main(String[] args) {
        String s = "42";
//        assert 42 == solution(s);

        s = "2147483648";
        assert 2147483647 == solution(s);

        s = "2147483646";
        assert 2147483646 == solution(s);

        s = "  -42";
        assert -42 == solution(s);

        s = "4193 with words";
        assert 4193 == solution(s);

        s = "-91283472332";
        assert -2147483648 == solution(s);

        s = "91283472332";
        assert 2147483647 == solution(s);

        s = "words and 987";
        assert 0 == solution(s);

        s = "+1";
        assert 1 == solution(s);

        s = "+-12";
        assert 0 == solution(s);
    }

    private static int solution(String s) {
        int res = 0;
        boolean isNegative = false;

        if (s.length() == 0) {
            return res;
        }

        //find first digit
        int startIndex = 0;
        boolean signFound = false;
        while (startIndex < s.length()) {
            char ch = s.charAt(startIndex);
            if (ch == ' ' && !signFound) {
                startIndex++;
                continue;
            }
            if (ch == '-' && !signFound) {
                signFound = true;
                isNegative = true;
                startIndex++;
                continue;
            }
            if (ch == '+' && !signFound) {
                signFound = true;
                startIndex++;
                continue;
            }
            int value = ch - '0';
            if (value < 0 && value > 9) {
                return res;
            } else {
                break;
            }
        }

        for (int i = startIndex; i < s.length(); i++) {
            char ch = s.charAt(i);
            int value = ch - '0';
            if (value >= 0 && value <= 9) {
                if (isNegative) {
                    if (res < Integer.MIN_VALUE / 10 ||
                            (
                                    (res <= Integer.MIN_VALUE / 10) && (-value <= Integer.MIN_VALUE % 10)
                            )
                    ) {
                        return Integer.MIN_VALUE;
                    }
                    value = -value;
                } else {
                    if (res > Integer.MAX_VALUE / 10 ||
                            (
                                    (res >= Integer.MAX_VALUE / 10) && (value >= Integer.MAX_VALUE % 10)
                            )
                    ) {
                        return Integer.MAX_VALUE;
                    }
                }
                res *= 10;
                res += value;
            } else {
                return res;
            }
        }
        return res;
    }
}
