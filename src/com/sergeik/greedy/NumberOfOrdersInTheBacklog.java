package com.sergeik.greedy;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 2D integer array orders, where each orders[i] = [pricei, amounti, orderTypei] denotes that
 * amounti orders have been placed of type orderTypei at the price pricei. The orderTypei is:
 *
 * 0 if it is a batch of buy orders, or
 * 1 if it is a batch of sell orders.
 * Note that orders[i] represents a batch of amounti independent orders with the same price and order type.
 * All orders represented by orders[i] will be placed before all orders represented by orders[i+1] for all valid i.
 *
 * There is a backlog that consists of orders that have not been executed. The backlog is initially empty.
 * When an order is placed, the following happens:
 *
 * If the order is a buy order, you look at the sell order with the smallest price in the backlog. If that sell
 * order's price is smaller than or equal to the current buy order's price, they will match and be executed,
 * and that sell order will be removed from the backlog. Else, the buy order is added to the backlog.
 * Vice versa, if the order is a sell order, you look at the buy order with the largest price in the backlog.
 * If that buy order's price is larger than or equal to the current sell order's price, they will match and be executed,
 * and that buy order will be removed from the backlog. Else, the sell order is added to the backlog.
 * Return the total amount of orders in the backlog after placing all the orders from the input.
 * Since this number can be large, return it modulo 109 + 7.
 */
public class NumberOfOrdersInTheBacklog {

    public static void main(String[] args) {
        assert 6 == solution(new int[][]{
                {10,5,0},
                {15,2,1},
                {25,1,1},
                {30,4,0}
        });
    }

    private static int solution(int[][] orders) {
        //buy = 0
        //sell = 1
        Queue<int[]> buy = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        Queue<int[]> sell = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        for (int[] order: orders) {
            if (order[2] == 0)
                buy.offer(order);
            else
                sell.offer(order);
            while(!buy.isEmpty() && !sell.isEmpty() && buy.peek()[0] >= sell.peek()[0]) {
                int min = Math.min(buy.peek()[1], sell.peek()[1]);
                buy.peek()[1] -= min;
                sell.peek()[1] -= min;
                if (buy.peek()[1] == 0)
                    buy.poll();
                if (sell.peek()[1] == 0)
                    sell.poll();
            }
        }

        int bl = 0;
        int mod = 1000000007;
        while(!buy.isEmpty())
            bl = (bl + buy.poll()[1]) % mod;
        while(!sell.isEmpty())
            bl = (bl + sell.poll()[1]) % mod;
        return bl;
    }

}
