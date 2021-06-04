package com.sergeik.strings;

/**
 * Given a string s consisting only of characters 'a', 'b', and 'c'. You are asked to apply the following
 * algorithm on the string any number of times:
 *
 * Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
 * Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
 * The prefix and the suffix should not intersect at any index.
 * The characters from the prefix and suffix must be the same.
 * Delete both the prefix and the suffix.
 * Return the minimum length of s after performing the above operation any number of times (possibly zero times).
 */
public class MinimumLengthOfStringAfterDeletingSimilarEnds {

    public static void main(String[] args) {
        assert 1 == solution("bbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbccbcbcbccbbabbb");
        assert 2 == solution("ca");
        assert 0 == solution("cabaabac");
        assert 3 == solution("aabccabba");
    }

    private static int solution(String s) {
        if (s.length() <= 1)
            return s.length();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
                while (s.charAt(start - 1) == s.charAt(start) && start <= end)
                    start++;
                while (s.charAt(end + 1) == s.charAt(end) && start <= end)
                    end--;
            } else {
                break;
            }
        }
        return end - start + 1;
    }

}
