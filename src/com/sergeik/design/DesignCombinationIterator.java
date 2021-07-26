package com.sergeik.design;

import java.util.ArrayList;

/**
 * Design the CombinationIterator class:
 *
 * CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters
 * of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * next() Returns the next combination of length combinationLength in lexicographical order.
 * hasNext() Returns true if and only if there exists a next combination.
 */
public class DesignCombinationIterator {

    public static void main(String[] args) {
        CombinationIterator itr;

        itr = new CombinationIterator("ahijp", 2);
        assert itr.hasNext();
        assert "ah".equals(itr.next());
        assert "ai".equals(itr.next());
        assert itr.hasNext();
        assert "aj".equals(itr.next());
        assert itr.hasNext();
        assert "ap".equals(itr.next());
        assert itr.hasNext();
        assert "hi".equals(itr.next());
        assert "hj".equals(itr.next());

        itr = new CombinationIterator("abc", 2);
        assert "ab".equals(itr.next());    // return "ab"
        assert itr.hasNext(); // return True
        assert "ac".equals(itr.next());    // return "ac"
        assert itr.hasNext(); // return True
        assert "bc".equals(itr.next());    // return "bc"
        assert !itr.hasNext(); // return False
    }

    static class CombinationIterator {

        ArrayList<String> combinations;
        int idx = 0;

        public CombinationIterator(String characters, int combinationLength) {
            combinations = new ArrayList<>();
            populate(characters, combinationLength);
        }

        private void populate(String characters, int combinationLength) {
            StringBuilder comb = new StringBuilder();
            dfs(comb, 0, characters.toCharArray(), combinationLength);
            return;
        }

        private void dfs(StringBuilder sb, int idx, char[] chars, int length) {
            if (sb.length() == length) {
                combinations.add(sb.toString());
                return;
            }
            for (int i = idx; i < chars.length; i++) {
                sb.append(chars[i]);
                dfs(sb, i + 1, chars, length);
                sb.setLength(sb.length() - 1);
            }
        }

        public String next() {
            return combinations.get(idx++);
        }

        public boolean hasNext() {
            return idx < combinations.size();
        }
    }

}
