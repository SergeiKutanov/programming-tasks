package com.sergeik.design;

import java.util.Stack;

/**
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price
 * for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and
 * going backward) for which the stock price was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100,80,60,70,60,75,85], then the stock spans would
 * be [1,1,1,2,1,4,6].
 * Implement the StockSpanner class:
 *
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 */
public class OnlineStockSpan {

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        assert 1 == stockSpanner.next(100); // return 1
        assert 1 == stockSpanner.next(80);  // return 1
        assert 1 == stockSpanner.next(60);  // return 1
        assert 2 == stockSpanner.next(70);  // return 2
        assert 1 == stockSpanner.next(60);  // return 1
        assert 4 == stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        assert 6 == stockSpanner.next(85);  // return 6
    }

    static class StockSpanner {

        Stack<int[]> stack = new Stack<>();

        public StockSpanner() {

        }

        public int next(int price) {
           int span = 1;
           while (!stack.isEmpty() && stack.peek()[0] <= price) {
               span += stack.pop()[1];
           }
           stack.push(new int[] {price, span});
           return span;
        }
    }

}
