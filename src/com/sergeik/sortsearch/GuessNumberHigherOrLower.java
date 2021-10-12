package com.sergeik.sortsearch;

public class GuessNumberHigherOrLower {

    private static int pick;

    public static void main(String[] args) {
        pick = 1702766719;
        assert pick == guessNumber(2126753390);
    }

    private static int guessNumber(int n) {
        int left = 1, right = n;
        while (true) {
            int middle = (right - left) / 2 + left;
            int res = guess(middle);
            if (res < 0)
                right = middle - 1;
            else if (res > 0)
                left = middle + 1;
            else
                return middle;
        }
    }

    private static int guess(int n) {
        if (n < pick)
            return 1;
        if (n > pick)
            return -1;
        return 0;
    }

}
