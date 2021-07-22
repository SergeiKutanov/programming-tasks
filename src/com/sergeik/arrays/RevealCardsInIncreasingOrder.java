package com.sergeik.arrays;

import java.util.*;

/**
 * In a deck of cards, every card has a unique integer.  You can order the deck in any order you want.
 *
 * Initially, all the cards start face down (unrevealed) in one deck.
 *
 * Now, you do the following steps repeatedly, until all cards are revealed:
 *
 * Take the top card of the deck, reveal it, and take it out of the deck.
 * If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
 * If there are still unrevealed cards, go back to step 1.  Otherwise, stop.
 * Return an ordering of the deck that would reveal the cards in increasing order.
 *
 * The first entry in the answer is considered to be the top of the deck.
 */
public class RevealCardsInIncreasingOrder {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {1,3,2,4}, queueSolution(new int[] {4,3,2,1}));
        assert Arrays.equals(new int[] {2,13,3,11,5,17,7}, queueSolution(new int[] {17,13,11,2,3,5,7}));
        assert Arrays.equals(new int[] {1,3,2,4}, solution(new int[] {4,3,2,1}));
        assert Arrays.equals(new int[] {2,13,3,11,5,17,7}, solution(new int[] {17,13,11,2,3,5,7}));
    }

    private static int[] solution(int[] deck) {
        Deque<Integer> deque = new ArrayDeque<>();
        Arrays.sort(deck);
        for (int i = deck.length - 1; i >= 0; i--) {
            if (!deque.isEmpty()) {
                int last = deque.pollLast();
                deque.offerFirst(last);
            }
            deque.offerFirst(deck[i]);
        }
        int[] ans = new int[deck.length];
        int idx = 0;
        while (!deque.isEmpty())
            ans[idx++] = deque.pollFirst();
        return ans;
    }

    private static int[] queueSolution(int[] deck) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < deck.length; i++)
            queue.offer(i);
        Arrays.sort(deck);
        int[] ans = new int[deck.length];
        for (int card: deck) {
            ans[queue.poll()] = card;
            if (!queue.isEmpty())
                queue.offer(queue.poll());
        }
        return ans;
    }
}
