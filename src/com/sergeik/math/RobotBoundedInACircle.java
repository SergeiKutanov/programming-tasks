package com.sergeik.math;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive
 * one of three instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degrees to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 */
public class RobotBoundedInACircle {

    public static void main(String[] args) {
        assert solution("GLRLLGLL");
        assert solution("GGLLGG");
        assert !solution("GG");
        assert solution("GL");
    }

    private static boolean solution(String instructions) {
        int dir = 0;
        int[] pos = new int[]{0,0};
        for (int i = 0; i < instructions.length(); i++) {
            switch (instructions.charAt(i)) {
                case 'L':
                    dir = dir == 0 ? 3 : dir - 1;
                    break;
                case 'R':
                    dir = dir == 3 ? 0 : dir + 1;
                    break;
                case 'G':
                    switch (dir) {
                        case 0: pos[1]++; break;
                        case 1: pos[0]++; break;
                        case 2: pos[1]--; break;
                        case 3: pos[0]--; break;
                    }
            }
        }
        return dir != 0 || (pos[0] == 0 && pos[1] == 0);
    }

}
