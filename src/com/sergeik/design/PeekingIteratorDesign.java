package com.sergeik.design;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Design an iterator that supports the peek operation on a list in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 *
 * PeekingIterator(int[] nums) Initializes the object with the given integer array nums.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * bool hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 */
public class PeekingIteratorDesign {

    public static void main(String[] args) {
        PeekingIterator peekingIterator = new PeekingIterator(Arrays.asList(1, 2, 3).iterator()); // [1,2,3]
        assert 1 == peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
        assert 2 == peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
        assert 2 == peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
        assert 3 == peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
        assert !peekingIterator.hasNext(); // return False
    }

    static class PeekingIterator implements Iterator<Integer> {

        Iterator<Integer> iterator;
        Integer cache;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (cache == null)
                cache = iterator.next();
            return cache;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (cache != null) {
                Integer tmp = cache;
                cache = null;
                return tmp;
            }

            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return cache != null || iterator.hasNext();
        }
    }

}
