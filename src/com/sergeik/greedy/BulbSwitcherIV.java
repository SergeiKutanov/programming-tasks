package com.sergeik.greedy;

/**
 * There is a room with n bulbs, numbered from 0 to n - 1, arranged in a row from left to right. Initially,
 * all the bulbs are turned off.
 *
 * Your task is to obtain the configuration represented by target where target[i] is '1' if the ith bulb is
 * turned on and is '0' if it is turned off.
 *
 * You have a switch to flip the state of the bulb, a flip operation is defined as follows:
 *
 * Choose any bulb (index i) of your current configuration.
 * Flip each bulb from index i to index n - 1.
 * When any bulb is flipped it means that if it is '0' it changes to '1' and if it is '1' it changes to '0'.
 *
 * Return the minimum number of flips required to form target.
 */
public class BulbSwitcherIV {

    public static void main(String[] args) {
        assert 3 == solution("10111");
        assert 5 == solution("001011101");
    }

    private static int solution(String target) {
        int cnt = 0, state = 0;
        for (char b : target.toCharArray())
            if (b - '0' != state) {
                state = b - '0';
                ++cnt;
            }
        return cnt;
    }

    private static int modSolution(String target) {
        int res = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == '0') {
                if (res % 2 != 0)
                    res++;
            } else {
                if (res % 2 == 0)
                    res++;
            }
        }
        return res;
    }

}
