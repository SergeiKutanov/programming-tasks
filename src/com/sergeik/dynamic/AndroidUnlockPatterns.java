package com.sergeik.dynamic;

/**
 * Android devices have a special lock screen with a 3 x 3 grid of dots. Users can set an "unlock pattern" by
 * connecting the dots in a specific sequence, forming a series of joined line segments where each segment's endpoints
 * are two consecutive dots in the sequence. A sequence of k dots is a valid unlock pattern if both of the
 * following are true:
 *
 * All the dots in the sequence are distinct.
 * If the line segment connecting two consecutive dots in the sequence passes through the center of any other dot,
 * the other dot must have previously appeared in the sequence. No jumps through the center non-selected dots
 * are allowed.
 * For example, connecting dots 2 and 9 without dots 5 or 6 appearing beforehand is valid because the line from
 * dot 2 to dot 9 does not pass through the center of either dot 5 or 6.
 * However, connecting dots 1 and 3 without dot 2 appearing beforehand is invalid because the line from dot 1 to
 * dot 3 passes through the center of dot 2.
 */
public class AndroidUnlockPatterns {

    private static int counter = 0;

    public static void main(String[] args) {
        assert 320 == solution(3,3);
        assert 385 == solution(1,3);
        assert 9 == solution(1, 1);
        assert 65 == solution(1, 2);
    }

    private static int solution(int m, int n) {
        counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean[] seen = new boolean[9];
                seen[i * 3 + j] = true;
                buildPaths(i, j, m, n, 1, seen);
            }

        }
        return counter;
    }

    private static void buildPaths(int r, int c, int m, int n, int len, boolean[] seen) {
        if (len >= m && len <= n)
            counter++;
        if (len == n)
            return;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int coor = i * 3 + j;
                if (seen[coor])
                    continue;
                boolean depsMet = true;
                if (Math.abs(r - i) != 1 && Math.abs(c - j) != 1) {
                    int x = (r == i) ? r : Math.max(i, r) - 1;
                    int y = (c == j) ? c : Math.max(j, c) - 1;
                    int depsCoor = x * 3 + y;
                    if (!seen[depsCoor])
                        depsMet = false;
                }
                if (depsMet) {
                    seen[coor] = true;
                    buildPaths(i, j, m, n, len + 1, seen);
                    seen[coor] = false;
                }
            }
        }
    }

}
