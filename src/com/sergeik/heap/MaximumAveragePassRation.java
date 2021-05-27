package com.sergeik.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There is a school that has classes of students and each class will be having a final exam. You are given
 * a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class,
 * there are totali total students, but only passi number of students will pass the exam.
 *
 * You are also given an integer extraStudents. There are another extraStudents brilliant students that are
 * guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents
 * students to a class in a way that maximizes the average pass ratio across all the classes.
 *
 * The pass ratio of a class is equal to the number of students of the class that will pass the exam divided
 * by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the
 * classes divided by the number of the classes.
 *
 * Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within
 * 10-5 of the actual answer will be accepted.
 */
public class MaximumAveragePassRation {

    public static void main(String[] args) {
        assert new Double(0.53485).equals(
                solution(new int[][]{
                        {2,4},
                        {3,9},
                        {4,5},
                        {2,10}
                }, 4)
        );
    }

    private static double solution(int[][] classes, int extraStudents) {
        Queue<double[]> heap = new PriorityQueue<>(Comparator.comparingDouble(a -> -a[0]));
        for (int[] cl: classes) {
            double a = cl[0];
            double b = cl[1];
            heap.offer(new double[]{ratioIncrease(a, b), a, b});
        }


        while (extraStudents-- > 0) {
            double[] top = heap.poll();
            double a = top[1] + 1;
            double b = top[2] + 1;
            heap.offer(new double[] {ratioIncrease(a, b), a, b});
        }

        double res = 0;
        while (!heap.isEmpty()) {
            double[] top = heap.poll();
            res += top[1] / top[2];
        }
        return res / classes.length;
    }

    private static double ratioIncrease(double a, double b) {
        return (a + 1) / (b + 1) - a / b;
    }

}
