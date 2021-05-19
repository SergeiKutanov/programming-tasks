package com.sergeik.arrays;

public class IsOneBitCharacter {

    public static void main(String[] args) {
        assert solution(new int[]{1,0,0});
        assert !solution(new int[]{1,1,1,0});
    }

    private static boolean solution(int[] bits) {
        return backtrack(bits, 0);
    }

    private static boolean backtrack(int[] bits, int start) {
        if (start >= bits.length)
            return false;
        if (start == bits.length - 1)
            return bits[start] == 0;
        if (bits[start] == 1) {
            start += 2;
        } else {
            start++;
        }
        return backtrack(bits, start);
    }

}
