package com.sergeik.math;

/**
 * You are working in a ball factory where you have n balls numbered from lowLimit up to highLimit inclusive
 * (i.e., n == highLimit - lowLimit + 1), and an infinite number of boxes numbered from 1 to infinity.
 *
 * Your job at this factory is to put each ball in the box with a number equal to the sum of digits of the ball's
 * number. For example, the ball number 321 will be put in the box number 3 + 2 + 1 = 6 and the ball number 10 will
 * be put in the box number 1 + 0 = 1.
 *
 * Given two integers lowLimit and highLimit, return the number of balls in the box with the most balls.
 */
public class MaximumNumberOfBallsInBoxes {

    public static void main(String[] args) {
        assert 2 == solution(5, 15);
    }

    private static int solution(int lowLimit, int highLimit) {
        int[] boxes = new int[46];
        int maxSoFar = 0;
        for (int ball = lowLimit; ball <= highLimit; ball++) {
            int sum = 0;
            int ballSize = ball;
            while (ballSize > 0) {
                sum += ballSize % 10;
                ballSize /= 10;
            }
            boxes[sum]++;
            maxSoFar = Math.max(maxSoFar, boxes[sum]);
        }
        return maxSoFar;
    }

}
