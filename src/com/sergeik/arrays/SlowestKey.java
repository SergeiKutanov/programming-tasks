package com.sergeik.arrays;

/**
 * A newly designed keypad was tested, where a tester pressed a sequence of n keys, one at a time.
 *
 * You are given a string keysPressed of length n, where keysPressed[i] was the ith key pressed in the testing
 * sequence, and a sorted list releaseTimes, where releaseTimes[i] was the time the ith key was released.
 * Both arrays are 0-indexed. The 0th key was pressed at the time 0, and every subsequent key was pressed at the
 * exact time the previous key was released.
 *
 * The tester wants to know the key of the keypress that had the longest duration. The ith keypress had a duration
 * of releaseTimes[i] - releaseTimes[i - 1], and the 0th keypress had a duration of releaseTimes[0].
 *
 * Note that the same key could have been pressed multiple times during the test, and these multiple presses of the
 * same key may not have had the same duration.
 *
 * Return the key of the keypress that had the longest duration. If there are multiple such keypresses, return the
 * lexicographically largest key of the keypresses.
 */
public class SlowestKey {

    public static void main(String[] args) {
        assert 'c' == solution(new int[] {9,29,49,50}, "cbcd");
        assert 'a' == solution(new int[] {12,23,36,46,62}, "spuda");
    }

    private static char solution(int[] releaseTimes, String keysPressed) {
        char ch = keysPressed.charAt(0);
        int longestTime = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            int cTime = releaseTimes[i] - releaseTimes[i - 1];
            if (cTime > longestTime) {
                longestTime = cTime;
                ch = keysPressed.charAt(i);
            } else if (cTime == longestTime && keysPressed.charAt(i) > ch) {
                ch = keysPressed.charAt(i);
            }
        }
        return ch;
    }

}
