package com.sergeik.greedy;

/**
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.
 *
 * The string is called alternating if no two adjacent characters are equal. For example, the strings
 * "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Any two characters may be swapped, even if they are not adjacent.
 */
public class MinimumNumberOfSwapsToMakeBinaryStringAlternating {

    public static void main(String[] args) {
        assert 65 == solution("00011110110110000000000110110101011101111011111101010010010000000000000001101101010010001011110000001101111111110000110101101101001011000011111011101101100110011111110001100110001110000000001100010111110100111001001111100001000110101111010011001");
        assert 3 == solution("11111000000");
        assert 1 == solution("100");
        assert 1 == solution("111000");
        assert 2 == solution("1111000");
    }

    private static int solution(String s) {
        int numberOfOnes = 0;
        int numOfZeros = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '1')
                numberOfOnes++;
            else
                numOfZeros++;

        if (Math.abs(numOfZeros - numberOfOnes) > 1)
            return -1;
        if (numberOfOnes > numOfZeros)
            return countDiff(s, '1');
        else if (numOfZeros > numberOfOnes)
            return countDiff(s, '0');
        return Math.min(countDiff(s, '0'), countDiff(s, '1'));
    }

    private static int countDiff(String s, char startChar) {
        int swaps = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != startChar)
                swaps++;
            startChar ^= 1;
        }
        return swaps / 2;
    }

}
