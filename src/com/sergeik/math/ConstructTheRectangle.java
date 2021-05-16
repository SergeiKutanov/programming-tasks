package com.sergeik.math;

import java.util.Arrays;

/**
 * A web developer needs to know how to design a web page's size. So, given a specific rectangular
 * web page’s area, your job by now is to design a rectangular web page, whose length L and width
 * W satisfy the following requirements:
 *
 * The area of the rectangular web page you designed must equal to the given target area.
 * The width W should not be larger than the length L, which means L >= W.
 * The difference between length L and width W should be as small as possible.
 * Return an array [L, W] where L and W are the length and width of the web page you designed in sequence.
 */
public class ConstructTheRectangle {

    public static void main(String[] args) {
        assert Arrays.equals(
                solution(4),
                new int[]{2,2}
        );
        assert Arrays.equals(solution(37), new int[]{37, 1});
        assert Arrays.equals(solution(122122), new int[]{427, 286});
    }

    /**
     * @param area
     * @return
     */
    private static int[] solution(int area) {
       int width = (int) Math.sqrt(area);
       while (area % width != 0)
           width--;
       return new int[]{area / width, width};
    }

}
