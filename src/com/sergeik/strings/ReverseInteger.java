package com.sergeik.strings;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. 
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 */
public class ReverseInteger {

    public static void main(String[] args) {
        int x = 123;
        assert 321 == solution(x);
        assert 321 == solutionBest(x);

        x = -123;
        assert -321 == solution(x);
        assert -321 == solutionBest(x);

        x = 120;
        assert 21 == solution(x);
        assert 21 == solutionBest(x);

        x = 0;
        assert 0 == solution(x);
        assert 0 == solutionBest(x);

        x = 1534236469;
        assert 0 == solution(x);
        assert 0 == solutionBest(x);
    }

    private static int solutionBest(int x) {
        int rev = 0;
        int maxThresh = Integer.MAX_VALUE / 10;
        int minThresh = Integer.MIN_VALUE / 10;
        int maxPop = Integer.MAX_VALUE % 10; // 7
        int minPop = Integer.MIN_VALUE % 10; // -8
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > maxThresh || (rev == maxThresh && pop > maxPop)) return 0;
            if (rev < minThresh || (rev == minThresh && pop < minPop)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * Solution with long
     * @param x
     * @return
     */
    private static int solution(int x) {
        boolean isNegative = x < 0;
        char[] originalInt = String.valueOf(x).toCharArray();
        int reversedInt = 0;
        long order = 1;
        for (int i = 0; i < originalInt.length; i++) {
            char ch = originalInt[i];
            //skip sign if present
            if (ch == '-') continue;
            //check overflow
            long valueToAdd = (ch - '0') * order;
            if (Integer.MAX_VALUE - valueToAdd < reversedInt) {
                return 0;
            }

            reversedInt += valueToAdd;
            order *= 10;
        }
        if (isNegative) {
            reversedInt = -reversedInt;
        }

        return reversedInt;
    }
    
}
